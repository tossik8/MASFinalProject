package nikita.toropov.masfinalproject.model;

import nikita.toropov.masfinalproject.model.account.Account;
import nikita.toropov.masfinalproject.model.account.CheckingAccount;
import nikita.toropov.masfinalproject.model.account.InvestmentAccount;
import nikita.toropov.masfinalproject.model.account.SavingsAccount;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.model.person.Credentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {


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
    public void testChangeBalance(){
        Account savingsAccount = SavingsAccount.builder()
                .interestRate(0.03f)
                .owner(client)
                .build();
        Account investmentAccount = InvestmentAccount.builder()
                .owner(client)
                .investmentObjective("Security")
                .build();
        Account checkingAccount = CheckingAccount.builder()
                .owner(client)
                .overdraftLimit(100)
                .build();

        assertThrows(IllegalArgumentException.class, () -> savingsAccount.setBalance(-1));
        assertThrows(IllegalArgumentException.class, () -> investmentAccount.setBalance(-1));
        assertThrows(IllegalStateException.class, () -> checkingAccount.setBalance(-101));

        savingsAccount.setBalance(100);
        investmentAccount.setBalance(1);
        checkingAccount.setBalance(-99);

        assertEquals(100, savingsAccount.getBalance());
        assertEquals(1, investmentAccount.getBalance());
        assertEquals(-99, checkingAccount.getBalance());
    }
}
