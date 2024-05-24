package nikita.toropov.masfinalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.model.person.Credentials;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
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
    public void testRegisterClient(){
        Branch branch = Branch.builder()
                .name("B")
                .address("Koszykowa 86")
                .build();
        branchRepository.save(branch);
        Client client = Client.builder()
                .name("Mike")
                .surname("Geller")
                .credentials(new Credentials("fsfs", "fdsfsdffdsdf"))
                .registeredAt(branch)
                .build();
        clientRepository.save(client);
        entityManager.flush();
        entityManager.refresh(branch);
        entityManager.refresh(client);

        assertEquals(branch, client.getRegisteredAt());
        assertTrue(branch.getClients().contains(client));
    }

    @Test
    public void testDeleteBranch(){
        Branch branch = Branch.builder()
                .name("B")
                .address("Koszykowa 86")
                .build();
        branchRepository.save(branch);
        Client client = Client.builder()
                .name("Mike")
                .surname("Geller")
                .credentials(new Credentials("fsfs", "fdsfsdffdsdf"))
                .registeredAt(branch)
                .build();
        clientRepository.save(client);
        entityManager.flush();
        entityManager.refresh(branch);

        branchRepository.delete(branch);
        entityManager.flush();
        entityManager.refresh(client);

        assertFalse(branchRepository.existsById(branch.getId()));
        assertNull(client.getRegisteredAt());
    }
}
