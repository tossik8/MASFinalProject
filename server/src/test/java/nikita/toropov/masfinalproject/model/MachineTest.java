package nikita.toropov.masfinalproject.model;

import nikita.toropov.masfinalproject.model.account.Account;
import nikita.toropov.masfinalproject.model.account.SavingsAccount;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.model.person.Credentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MachineTest {

    Account account;

    @BeforeEach
    public void setup(){
        Branch branch = Branch.builder()
                .name("B")
                .address("Koszykowa 86")
                .build();
        Client client = Client.builder()
                .name("Mike")
                .surname("Geller")
                .credentials(new Credentials("mikegeller@gmail.com", "123422423"))
                .registeredAt(branch)
                .build();
        account = SavingsAccount.builder()
                .interestRate(0.03f)
                .owner(client)
                .build();
    }

    @Test
    public void testDeposit(){
        Machine machine = Machine.builder()
                .model("3000A")
                .type(EnumSet.of(Machine.MachineType.DEPOSIT))
                .build();
        machine.deposit(account, 100);

        assertEquals(100, machine.getBalance());
        assertEquals(100, account.getBalance());
        assertThrows(IllegalArgumentException.class, () -> machine.deposit(account, -1));
        assertThrows(IllegalStateException.class, () -> machine.withdraw(account, 1));
        assertThrows(IllegalArgumentException.class, () -> machine.deposit(null, 10));
    }

    @Test
    public void testWithdraw(){
        Machine machine = Machine.builder()
                .model("3000A")
                .type(EnumSet.of(Machine.MachineType.WITHDRAWAL))
                .build();
        machine.setBalance(150);
        account.setBalance(100);
        machine.withdraw(account, 80);

        assertEquals(70, machine.getBalance());
        assertEquals(20, account.getBalance());
        assertThrows(IllegalArgumentException.class, () -> machine.withdraw(account, -1));
        assertThrows(IllegalStateException.class, () -> machine.deposit(account, 100));
        assertThrows(IllegalArgumentException.class, () -> machine.withdraw(null, 10));
    }
}
