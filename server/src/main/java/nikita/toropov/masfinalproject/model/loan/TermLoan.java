package nikita.toropov.masfinalproject.model.loan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.*;
import nikita.toropov.masfinalproject.model.person.Client;

import java.time.LocalDate;
import java.time.Period;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class TermLoan extends Loan{

    @Column(updatable = false)
    private LocalDate maturityDate;

    @Min(0)
    @Column(updatable = false)
    private int principal;

    @Builder
    public TermLoan(Client owner, int principal, float interestRate, LocalDate maturityDate, Collateral collateral) {
        super(owner, principal, interestRate, collateral);
        setPrincipal(principal);
        setMaturityDate(maturityDate);
    }

    /**
     * Sets the maturity date of the loan account.
     *
     * @param maturityDate the maturity date to set for the loan account.
     * @throws IllegalArgumentException if the {@code maturityDate} is null.
     * @throws IllegalStateException if the {@code maturityDate} is before the opening date of the account.
     */
    public void setMaturityDate(LocalDate maturityDate) {
        if(maturityDate == null){
            throw new IllegalArgumentException("Invalid maturity date");
        }
        if(getOpeningDate().isAfter(maturityDate)){
            throw new IllegalStateException("Maturity date cannot be before opening date");
        }
        this.maturityDate = maturityDate;
    }

    /**
     * Calculates the difference in months between the maturity date and the opening date of the account.
     *
     * @return the difference in months between the maturity date and the opening date.
     */
    private int calculateDifferenceInMonths(){
        Period period = Period.between(this.getOpeningDate(), this.getMaturityDate());
        return period.getMonths() + period.getYears() * 12;
    }

    /**
     * Computes the monthly payment amount for the loan account using the amortization formula.
     *
     * @return the calculated monthly payment amount for the loan account.
     */
    private float getLoanPayment(){
        int totalMonthDifference = calculateDifferenceInMonths();
        double monthlyRate = this.getInterestRate() / 12;
        float payment = (float) (principal * (monthlyRate * Math.pow(1 + monthlyRate, totalMonthDifference) /
                        (Math.pow(1 + monthlyRate, totalMonthDifference) - 1)));
        return Math.round(payment * 100) / 100.0f;
    }


    /**
     * Retrieves the payment amount for the loan account.
     *
     * @return the calculated monthly payment amount for the loan account.
     * {@link #getLoanPayment()}
     */
    @Override
    public float getPayment() {
        return getLoanPayment();
    }
}
