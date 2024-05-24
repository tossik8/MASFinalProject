package nikita.toropov.masfinalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.model.person.Credentials;
import nikita.toropov.masfinalproject.repository.account.AccountRepository;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BranchRepository branchRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreateClient(){
        Branch branch = branchRepository.findById(1000L).orElseThrow();
        Client client = Client.builder()
                .name("Mike")
                .surname("Geller")
                .credentials(new Credentials("mikegeller@gmail.com", "123422423"))
                .registeredAt(branch)
                .build();
        clientRepository.save(client);
        entityManager.flush();
        entityManager.refresh(client);

        assertEquals("Mike", client.getName());
        assertEquals("Geller", client.getSurname());
        assertEquals("mikegeller@gmail.com", client.getCredentials().getEmail());
        assertEquals("123422423", client.getCredentials().getPassword());
        assertEquals(branch, client.getRegisteredAt());
        assertTrue(branch.getClients().contains(client));
        assertTrue(client.getAccounts().isEmpty());
    }

    @Test
    public void testRemoveClient(){
        Client client = clientRepository.findById(1000L).orElseThrow();
        Branch branch = client.getRegisteredAt();

        assertEquals(1, client.getAccounts().size());
        assertEquals(1, client.getLoans().size());

        clientRepository.delete(client);

        assertFalse(clientRepository.existsById(1000L));
        assertEquals(0, accountRepository.count());
        assertEquals(0, loanRepository.count());
        assertFalse(branch.getClients().contains(client));
    }
}
