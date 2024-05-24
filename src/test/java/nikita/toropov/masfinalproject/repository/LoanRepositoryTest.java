package nikita.toropov.masfinalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nikita.toropov.masfinalproject.model.loan.*;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CollateralRepository collateralRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Client client;

    @BeforeEach
    public void setup(){
        client = clientRepository.findById(1000L).orElseThrow();
    }

    @Test
    public void testCreateRevolvingLoan(){
        RevolvingLoan loan = RevolvingLoan.builder()
                .creditLimit(1000)
                .interestRate(0.03f)
                .owner(client)
                .build();
        loanRepository.save(loan);
        entityManager.flush();

        assertEquals(1000, loan.getCreditLimit());
        assertEquals(0.03f, loan.getInterestRate());
        assertEquals(0, loan.getBalance());
        assertEquals(LocalDate.now(), loan.getOpeningDate());
        assertEquals(client, loan.getOwner());
        assertTrue(loanRepository.existsById(loan.getId()));
        assertTrue(client.getLoans().contains(loan));
        assertNull(loan.getCollateral());
    }

    @Test
    public void testCreateTermLoan(){
        Collateral collateral = Car.builder()
                .make("Ford")
                .model("Mustang")
                .price(100000)
                .yearBuilt(2000)
                .build();
        collateralRepository.save(collateral);
        TermLoan loan = TermLoan.builder()
                .interestRate(0.05f)
                .maturityDate(LocalDate.of(2025, 1, 1))
                .owner(client)
                .principal(10000)
                .collateral(collateral)
                .build();
        loanRepository.save(loan);
        entityManager.flush();
        entityManager.refresh(collateral);

        assertEquals(0.05f, loan.getInterestRate());
        assertEquals(10000, loan.getPrincipal());
        assertEquals(10000, loan.getBalance());
        assertEquals(LocalDate.of(2025, 1, 1), loan.getMaturityDate());
        assertEquals(LocalDate.now(), loan.getOpeningDate());
        assertEquals(client, loan.getOwner());
        assertTrue(loanRepository.existsById(loan.getId()));
        assertTrue(client.getLoans().contains(loan));
        assertEquals(collateral, loan.getCollateral());
        assertEquals(loan, collateral.getLoan());
    }

    @Test
    public void testFetchLoan(){
        Loan loan = loanRepository.findById(1000L).orElseThrow();

        assertEquals(10000, ((TermLoan) loan).getPrincipal());
        assertEquals(LocalDate.of(2025, 1, 1), ((TermLoan) loan).getMaturityDate());
    }

    @Test
    public void testGetPayment(){
        Loan loan1 = RevolvingLoan.builder()
                .owner(client)
                .interestRate(0.05f)
                .creditLimit(500)
                .build();

        assertEquals(0, loan1.getPayment(), 0.001);

        loan1.setBalance(100);

        assertEquals(5, loan1.getPayment());
        assertThrows(IllegalStateException.class, () -> loan1.setBalance(501));

        Loan loan2 = TermLoan.builder()
                .owner(client)
                .principal(10000)
                .maturityDate(LocalDate.of(2027, 6, 23))
                .interestRate(0.05f).build();

        assertEquals(292.2, loan2.getPayment(), 0.001);
    }

    @Test
    public void testDeleteLoan(){
        Collateral collateral = Car.builder()
                .make("Ford")
                .model("Mustang")
                .price(100000)
                .yearBuilt(2000)
                .build();
        collateralRepository.save(collateral);
        TermLoan loan = TermLoan.builder()
                .interestRate(0.05f)
                .maturityDate(LocalDate.of(2025, 1, 1))
                .owner(client)
                .principal(10000)
                .collateral(collateral)
                .build();
        loanRepository.save(loan);
        entityManager.flush();
        entityManager.refresh(collateral);

        loanRepository.delete(loan);
        entityManager.flush();

        assertFalse(loanRepository.existsById(loan.getId()));
        assertFalse(collateralRepository.existsById(collateral.getId()));
    }
}
