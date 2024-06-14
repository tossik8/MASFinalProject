package nikita.toropov.masfinalproject.model.account;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.*;
import nikita.toropov.masfinalproject.model.person.Client;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class CheckingAccount extends Account{

    @Min(0)
    private int overdraftLimit;

    @Builder
    public CheckingAccount(Client owner, int overdraftLimit){
        super(owner);
        setOverdraftLimit(overdraftLimit);
    }

    /**
     * Sets the balance of the account, ensuring it does not exceed the overdraft limit.
     *
     * @param balance the balance to set for the account, which should not be less than {@code -overdraftLimit}.
     * @throws IllegalStateException if the specified {@code balance} is smaller than the overdraft limit.
     */
    @Override
    public void setBalance(float balance) {
        if(balance < -overdraftLimit){
            throw new IllegalStateException("Balance cannot smaller than the overdraft limit");
        }
        this.balance = balance;
    }
}
