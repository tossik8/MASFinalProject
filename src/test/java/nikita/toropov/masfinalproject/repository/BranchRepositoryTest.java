package nikita.toropov.masfinalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.model.person.Credentials;
import nikita.toropov.masfinalproject.model.person.employee.Employee;
import nikita.toropov.masfinalproject.model.person.employee.FullTimeEmployee;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
import nikita.toropov.masfinalproject.repository.person.employee.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BranchRepositoryTest {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreateBranch(){
        Branch branch = Branch.builder()
                .name("A")
                .address("Koszykowa 86")
                .build();
        branchRepository.save(branch);
        entityManager.flush();

        assertEquals("A", branch.getName());
        assertEquals("Koszykowa 86", branch.getAddress());
        assertTrue(branchRepository.existsById(branch.getId()));
    }

    @Test
    public void testRemoveBranch(){
        Branch branch = Branch.builder()
                .name("B")
                .address("Koszykowa 86")
                .build();
        branchRepository.save(branch);
        Client client = Client.builder()
                .name("Mike")
                .surname("Geller")
                .credentials(new Credentials("mike@gmail.com", "fdsfsdffdsdf"))
                .registeredAt(branch)
                .build();
        Employee employee = FullTimeEmployee.builder()
                .name("Mike")
                .surname("Geller")
                .salary(10000)
                .worksAt(branch)
                .build();
        clientRepository.save(client);
        employeeRepository.save(employee);
        entityManager.flush();
        entityManager.refresh(branch);

        branchRepository.delete(branch);
        entityManager.flush();
        entityManager.refresh(client);
        entityManager.refresh(employee);

        assertFalse(branchRepository.existsById(branch.getId()));
        assertNull(client.getRegisteredAt());
        assertNull(employee.getWorksAt());
    }
}
