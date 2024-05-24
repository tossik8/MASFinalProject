package nikita.toropov.masfinalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nikita.toropov.masfinalproject.model.loan.Car;
import nikita.toropov.masfinalproject.model.loan.House;
import nikita.toropov.masfinalproject.model.loan.Loan;
import nikita.toropov.masfinalproject.model.loan.RevolvingLoan;
import nikita.toropov.masfinalproject.repository.person.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CollateralRepositoryTest {

    @Autowired
    private CollateralRepository collateralRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreateCar(){
        Car collateral = Car.builder()
                .make("Ford")
                .model("Mustang")
                .price(100000)
                .yearBuilt(2000)
                .build();
        collateralRepository.save(collateral);
        entityManager.flush();

        assertTrue(collateralRepository.existsById(collateral.getId()));
        assertEquals("Ford", collateral.getMake());
        assertEquals("Mustang", collateral.getModel());
        assertEquals(100000, collateral.getPrice());
        assertEquals(2000, collateral.getYearBuilt());
        assertNull(collateral.getLoan());
    }

    @Test
    public void testCreateHouse(){
        House collateral = House.builder()
                .address("Koszykowa 86")
                .area(67.8f)
                .rooms(2)
                .price(100000)
                .yearBuilt(2000)
                .build();
        collateralRepository.save(collateral);
        entityManager.flush();

        assertTrue(collateralRepository.existsById(collateral.getId()));
        assertEquals("Koszykowa 86", collateral.getAddress());
        assertEquals(67.8f, collateral.getArea());
        assertEquals(2, collateral.getRooms());
        assertEquals(100000, collateral.getPrice());
        assertEquals(2000, collateral.getYearBuilt());
        assertNull(collateral.getLoan());
    }

    @Test
    public void deleteCollateral(){
        House collateral = House.builder()
                .address("Koszykowa 86")
                .area(67.8f)
                .rooms(2)
                .price(100000)
                .yearBuilt(2000)
                .build();
        Loan loan = RevolvingLoan.builder()
                .owner(clientRepository.findById(1000L).orElseThrow())
                .interestRate(0.05f)
                .creditLimit(500)
                .collateral(collateral)
                .build();
        collateralRepository.save(collateral);
        loanRepository.save(loan);
        entityManager.flush();
        entityManager.refresh(collateral);

        collateralRepository.delete(collateral);
        entityManager.flush();
        entityManager.refresh(loan);

        assertNull(loan.getCollateral());
    }
}
