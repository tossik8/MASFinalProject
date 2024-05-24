package nikita.toropov.masfinalproject.model;

import nikita.toropov.masfinalproject.model.account.Account;
import nikita.toropov.masfinalproject.model.account.CheckingAccount;
import nikita.toropov.masfinalproject.model.account.InvestmentAccount;
import nikita.toropov.masfinalproject.model.account.SavingsAccount;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.model.person.Credentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    Branch branch;

    @BeforeEach
    public void setup(){
        branch = Branch.builder()
                .name("B")
                .address("Koszykowa 86")
                .build();
    }

    @Test
    public void testGetAccounts(){
        Client client = Client.builder()
                .name("Mike")
                .surname("Geller")
                .credentials(new Credentials("mikegeller@gmail.com", "12345678"))
                .registeredAt(branch)
                .build();
        InvestmentAccount investmentAccount = InvestmentAccount.builder()
                .investmentObjective("Safety")
                .owner(client)
                .build();
        investmentAccount.setOpeningDate(LocalDate.of(2023, 1, 10));
        CheckingAccount checkingAccount = CheckingAccount.builder()
                .overdraftLimit(1000)
                .owner(client)
                .build();
        checkingAccount.setOpeningDate(LocalDate.of(2022, 7, 7));
        SavingsAccount savingsAccount = SavingsAccount.builder()
                .owner(client)
                .interestRate(0.5f)
                .build();
        client.setAccounts(Set.of(savingsAccount, investmentAccount, checkingAccount));
        List<Account> accounts = client.getAccounts();

        assertEquals(checkingAccount, accounts.get(0));
        assertEquals(investmentAccount, accounts.get(1));
        assertEquals(savingsAccount, accounts.get(2));
    }
}
