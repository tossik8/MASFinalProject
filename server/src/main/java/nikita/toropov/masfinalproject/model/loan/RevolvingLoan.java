package nikita.toropov.masfinalproject.model.loan;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.*;
import nikita.toropov.masfinalproject.model.person.Client;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class RevolvingLoan extends Loan{

    @Min(0)
    private int creditLimit;

    @Builder
    public RevolvingLoan(Client owner, int creditLimit, float interestRate, Collateral collateral) {
        super(owner, 0, interestRate, collateral);
        setCreditLimit(creditLimit);
    }

    @Override
    public void setBalance(float balance) {
        if(balance > creditLimit){
            throw new IllegalStateException("Balance cannot exceed credit limit");
        }
        super.setBalance(balance);
    }

    /**
     * Calculates the minimum payment based on the balance and interest rate.
     *
     * @return the calculated minimum payment for the account.
     */
    private float getMinimumPayment(){
        return Math.round(this.getBalance() * this.getInterestRate() * 100) / 100.0f;
    }

    /**
     * Retrieves the payment amount for the account.
     *
     * @return the calculated minimum payment amount for the account.
     * {@link #getMinimumPayment()}.
     */
    @Override
    public float getPayment() {
        return getMinimumPayment();
    }
}
