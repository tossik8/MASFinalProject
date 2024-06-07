package nikita.toropov.masfinalproject.model;

import nikita.toropov.masfinalproject.model.loan.Loan;
import nikita.toropov.masfinalproject.model.loan.RevolvingLoan;
import nikita.toropov.masfinalproject.model.loan.TermLoan;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.model.person.Credentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoanTest {

    Client client;

    @BeforeEach
    public void setup(){
        Branch branch = Branch.builder()
                .name("B")
                .address("Koszykowa 86")
                .build();
        client = Client.builder()
                .name("Mike")
                .surname("Geller")
                .credentials(new Credentials("mikegeller@gmail.com", "123422423"))
                .registeredAt(branch)
                .build();
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

        Loan loan2 = TermLoan.builder()
                .owner(client)
                .principal(10000)
                .maturityDate(LocalDate.now().plusYears(3))
                .interestRate(0.05f)
                .build();

        assertEquals(299.71, loan2.getPayment(), 0.001);
    }
}
