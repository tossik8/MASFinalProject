package nikita.toropov.masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import nikita.toropov.masfinalproject.model.account.Account;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.EnumSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Machine {

    public enum MachineType{DEPOSIT, WITHDRAWAL}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String model;

    @Min(0)
    private int balance;

    @ElementCollection
    @CollectionTable(name = "machine_type", joinColumns = @JoinColumn(name = "machine_id"))
    @Setter(AccessLevel.NONE)
    @NotNull
    @Size(min = 1, max = 2)
    @Valid
    private Set<MachineType> type;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Branch branch;

    @Builder
    public Machine(String model, EnumSet<MachineType> type, Branch branch){
        setModel(model);
        setBranch(branch);
        this.type = type;
    }

    /**
     * Deposits a specified amount into the given account, updating the machine and account balances.
     *
     * @param account the account into which to deposit the amount.
     * @param amount the amount to deposit into the account, which must be greater than zero.
     * @throws IllegalStateException if the machine does not support deposits.
     * @throws IllegalArgumentException if the {@code account} is {@code null} or if the {@code amount} is not positive.
     */
    public void deposit(Account account, int amount){
        if(!getType().contains(MachineType.DEPOSIT)){
            throw new IllegalStateException("The machine does not support deposits");
        }
        if(account == null){
            throw new IllegalArgumentException("Invalid account");
        }
        if(amount <= 0){
            throw new IllegalArgumentException("Invalid amount");
        }
        account.setBalance(account.getBalance() + amount);
        setBalance(getBalance() + amount);
    }

    /**
     * Withdraws a specified amount from the given account, updating the machine and account balances.
     *
     * @param account the account from which to withdraw the amount.
     * @param amount the amount to withdraw from the account, which must be greater than zero.
     * @throws IllegalStateException if the machine does not support withdrawals.
     * @throws IllegalArgumentException if the {@code account} is {@code null} or if the {@code amount} is not positive.
     */
    public void withdraw(Account account, int amount){
        if(!getType().contains(MachineType.WITHDRAWAL)){
            throw new IllegalStateException("The machine does not support withdrawals");
        }
        if(account == null){
            throw new IllegalArgumentException("Invalid account");
        }
        if(amount <= 0){
            throw new IllegalArgumentException("Invalid amount");
        }
        if(getBalance() < amount){
            System.out.println("The machine cannot dispense this amount");
            return;
        }
        account.setBalance(account.getBalance() - amount);
        setBalance(getBalance() - amount);
    }
}
