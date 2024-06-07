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

        assertTrue(loanRepository.existsById(loan.getId()));
        assertEquals(1000, loan.getCreditLimit());
        assertEquals(0.03f, loan.getInterestRate());
        assertEquals(0, loan.getBalance());
        assertEquals(LocalDate.now(), loan.getOpeningDate());
        assertEquals(client, loan.getOwner());
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
    public void testRemoveLoan(){
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
