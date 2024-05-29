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

    private float getMinimumPayment(){
        return Math.round(this.getBalance() * this.getInterestRate() * 100) / 100.0f;
    }

    @Override
    public float getPayment() {
        return getMinimumPayment();
    }
}
