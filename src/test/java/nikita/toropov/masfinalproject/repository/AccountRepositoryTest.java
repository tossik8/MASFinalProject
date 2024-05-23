package nikita.toropov.masfinalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nikita.toropov.masfinalproject.model.PurchasedSecurity;
import nikita.toropov.masfinalproject.model.Security;
import nikita.toropov.masfinalproject.model.account.CheckingAccount;
import nikita.toropov.masfinalproject.model.account.InvestmentAccount;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.model.account.SavingsAccount;
import nikita.toropov.masfinalproject.repository.account.AccountRepository;
import nikita.toropov.masfinalproject.repository.account.CheckingAccountRepository;
import nikita.toropov.masfinalproject.repository.account.InvestmentAccountRepository;
import nikita.toropov.masfinalproject.repository.account.SavingsAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private InvestmentAccountRepository investmentAccountRepository;

    @Autowired
    private SecurityRepository securityRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PurchasedSecurityRepository purchasedSecurityRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    public void testCreateSavingsAccount(){
        Optional<Client> client = clientRepository.findById(1000L);
        SavingsAccount account = SavingsAccount.builder()
                .owner(client.orElseThrow())
                .interestRate(0.01f)
                .build();
        accountRepository.save(account);
        entityManager.flush();

        assertEquals(0.01f, account.getInterestRate());
        assertTrue(client.orElseThrow().getAccounts().contains(account));
        assertEquals(client.orElseThrow(), account.getOwner());
        assertTrue(account.getAccountNumber().startsWith("61 1090 1014"));
        assertEquals(LocalDate.now(), account.getOpeningDate());
    }

    @Test
    public void testCreateCheckingAccount(){
        Optional<Client> client = clientRepository.findById(1000L);
        CheckingAccount account = CheckingAccount.builder()
                .overdraftLimit(100)
                .owner(client.orElseThrow())
                .build();
        accountRepository.save(account);
        entityManager.flush();

        assertEquals(100, account.getOverdraftLimit());
        assertTrue(client.orElseThrow().getAccounts().contains(account));
        assertEquals(client.orElseThrow(), account.getOwner());
        assertTrue(account.getAccountNumber().startsWith("61 1090 1014"));
        assertEquals(LocalDate.now(), account.getOpeningDate());
    }

    @Test
    public void testCreateInvestmentAccount(){
        Optional<Client> client = clientRepository.findById(1000L);
        InvestmentAccount account = InvestmentAccount.builder()
                .investmentObjective("Safety")
                .owner(client.orElseThrow()).build();
        accountRepository.save(account);
        entityManager.flush();

        assertEquals("Safety", account.getInvestmentObjective());
        assertTrue(client.orElseThrow().getAccounts().contains(account));
        assertEquals(client.orElseThrow(), account.getOwner());
        assertTrue(account.getAccountNumber().startsWith("61 1090 1014"));
        assertEquals(LocalDate.now(), account.getOpeningDate());
    }

    @Test
    public void testFetchAccount(){
        Optional<CheckingAccount> account1 = checkingAccountRepository.findById(1000L);

        assertEquals(100, (account1.orElseThrow()).getOverdraftLimit());
    }

    @Test
    public void testBuySecurity(){
        Security security = Security.builder()
                .name("Apple stock")
                .code("APPL")
                .build();
        Optional<Client> client = clientRepository.findById(1000L);
        InvestmentAccount account = InvestmentAccount.builder()
                .investmentObjective("Safety")
                .owner(client.orElseThrow())
                .build();
        PurchasedSecurity purchasedSecurity = PurchasedSecurity.builder()
                .security(security)
                .investmentAccount(account)
                .price(53.57f)
                .quantity(2)
                .build();
        securityRepository.save(security);
        accountRepository.save(account);
        purchasedSecurityRepository.save(purchasedSecurity);
        entityManager.flush();
        entityManager.refresh(security);
        entityManager.refresh(account);

        assertEquals(53.57f, purchasedSecurity.getPrice());
        assertEquals(2, purchasedSecurity.getQuantity());
        assertEquals(LocalDate.now(), purchasedSecurity.getDate().toLocalDate());
        assertTrue(security.getPurchasedSecurities().contains(purchasedSecurity));
        assertTrue(account.getPurchasedSecurities().contains(purchasedSecurity));
        assertEquals(account, purchasedSecurity.getInvestmentAccount());
        assertEquals(security, purchasedSecurity.getSecurity());
    }
}
