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
     * Sets the account balance, ensuring it stays above the overdraft limit.
     *
     * @param balance The new balance (must be greater than or equal to -overdraftLimit).
     * @throws IllegalStateException if the balance falls below the overdraft limit.
     */
    @Override
    public void setBalance(float balance) {
        if(balance < -overdraftLimit){
            throw new IllegalStateException("Balance cannot smaller than the overdraft limit");
        }
        this.balance = balance;
    }
}
