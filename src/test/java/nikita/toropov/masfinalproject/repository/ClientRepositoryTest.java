package nikita.toropov.masfinalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.model.person.Credentials;
import nikita.toropov.masfinalproject.repository.account.AccountRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BranchRepository branchRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    public void testCreateInvalidClient(){
        Optional<Branch> branch = branchRepository.findById(1000L);
        Client client = Client.builder()
                .name("Mike")
                .surname("Geller")
                .credentials(Credentials.builder()
                        .email("mgeller@gmail.com")
                        .password("password")
                        .build())
                .registeredAt(branch.orElseThrow())
                .build();
        clientRepository.save(client);

        assertThrows(ConstraintViolationException.class, () -> entityManager.flush());
    }

    @Test
    public void testCreateValidClient(){
        Optional<Branch> branch = branchRepository.findById(1000L);
        Client client = Client.builder()
                .name("Mike")
                .surname("Geller")
                .credentials(new Credentials("mikegeller@gmail.com", "123422423"))
                .registeredAt(branch.orElseThrow())
                .build();
        clientRepository.save(client);
        entityManager.flush();

        assertEquals(branch.orElseThrow(), client.getRegisteredAt());
        assertTrue(branch.orElseThrow().getClients().contains(client));
        assertTrue(client.getAccounts().isEmpty());
    }

    @Test
    public void testDeleteClient(){
        Optional<Client> client = clientRepository.findById(1000L);
        Branch branch = client.orElseThrow().getRegisteredAt();
        assertEquals(1, client.orElseThrow().getAccounts().size());

        clientRepository.delete(client.orElseThrow());

        assertFalse(clientRepository.existsById(1000L));
        assertEquals(0, accountRepository.count());
        assertFalse(branch.getClients().contains(client.orElseThrow()));
    }
}
