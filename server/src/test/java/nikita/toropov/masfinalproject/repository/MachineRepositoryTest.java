package nikita.toropov.masfinalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.Machine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MachineRepositoryTest {

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private BranchRepository branchRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreateDepositMachine(){
        Machine depositMachine = Machine.builder()
                .model("3000A")
                .type(EnumSet.of(Machine.MachineType.DEPOSIT))
                .build();

        machineRepository.save(depositMachine);

        assertTrue(machineRepository.existsById(depositMachine.getId()));
        assertEquals("3000A", depositMachine.getModel());
        assertNull(depositMachine.getBranch());
        assertEquals(0, depositMachine.getBalance());
        assertTrue(depositMachine.getType().contains(Machine.MachineType.DEPOSIT));
    }

    @Test
    public void testCreateWithdrawalMachine(){
        Machine withdrawalMachine = Machine.builder()
                .model("3000B")
                .type(EnumSet.of(Machine.MachineType.WITHDRAWAL))
                .build();

        machineRepository.save(withdrawalMachine);

        assertTrue(machineRepository.existsById(withdrawalMachine.getId()));
        assertEquals("3000B", withdrawalMachine.getModel());
        assertNull(withdrawalMachine.getBranch());
        assertEquals(0, withdrawalMachine.getBalance());
        assertTrue(withdrawalMachine.getType().contains(Machine.MachineType.WITHDRAWAL));
    }

    @Test
    public void testCreateDepositWithdrawalMachine(){
        Machine machine = Machine.builder()
                .model("3000c")
                .type(EnumSet.of(Machine.MachineType.WITHDRAWAL, Machine.MachineType.DEPOSIT))
                .build();

        machineRepository.save(machine);

        assertTrue(machineRepository.existsById(machine.getId()));
        assertEquals("3000c", machine.getModel());
        assertEquals(0, machine.getBalance());
        assertNull(machine.getBranch());
        assertTrue(machine.getType()
                .containsAll(EnumSet.of
                        (Machine.MachineType.WITHDRAWAL, Machine.MachineType.DEPOSIT)));
    }

    @Test
    public void testCreateInvalidMachine(){
        Machine depositMachine = Machine.builder()
                .model("3000A")
                .type(null)
                .build();
        Machine depositMachine2 = Machine.builder()
                .model("3000A")
                .type(EnumSet.noneOf(Machine.MachineType.class))
                .build();

        assertThrows(ConstraintViolationException.class, () -> {
            machineRepository.save(depositMachine);
            entityManager.flush();
        });
        assertThrows(ConstraintViolationException.class, () -> {
            machineRepository.save(depositMachine2);
            entityManager.flush();
        });
    }

    @Test
    public void testDeleteMachine(){
        Branch branch = Branch.builder()
                .name("C")
                .address("Address")
                .build();
        Machine machine = Machine.builder()
                .model("3000c")
                .type(EnumSet.of(Machine.MachineType.WITHDRAWAL, Machine.MachineType.DEPOSIT))
                .branch(branch)
                .build();
        branchRepository.save(branch);
        machineRepository.save(machine);
        entityManager.flush();
        entityManager.refresh(branch);

        assertEquals(branch, machine.getBranch());
        assertTrue(branch.getMachines().contains(machine));

        machineRepository.delete(machine);
        entityManager.refresh(branch);

        assertFalse(machineRepository.existsById(machine.getId()));
        assertFalse(branch.getMachines().contains(machine));
    }
}
