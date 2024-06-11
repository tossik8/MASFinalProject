package nikita.toropov.masfinalproject.model.loan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.*;
import nikita.toropov.masfinalproject.model.person.Client;

import java.time.LocalDate;

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
     * Sets the loan's maturity date.
     *
     * @param maturityDate The date the loan matures (must not be null and must be after the opening date).
     * @throws IllegalArgumentException if maturityDate is null.
     * @throws IllegalStateException if maturityDate is before the loan's opening date.
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
     * Calculates total months between loan opening and maturity dates.
     *
     * @return The total difference in months between the opening and maturity dates.
     */
    private int calculateDifferenceInMonths(){
        int monthDifference = maturityDate.getMonthValue() - this.getOpeningDate().getMonthValue();
        int yearDifference = maturityDate.getYear() - this.getOpeningDate().getYear();
        return yearDifference * 12 + monthDifference;
    }

    /**
     * Calculates monthly loan payment using loan formula based on term and interest rate.
     *
     * @return The calculated monthly loan payment amount (rounded to two decimal places).
     */
    private float getLoanPayment(){
        int totalMonthDifference = calculateDifferenceInMonths();
        double monthlyRate = this.getInterestRate() / 12;
        float payment = (float) (principal * (monthlyRate * Math.pow(1 + monthlyRate, totalMonthDifference) /
                        (Math.pow(1 + monthlyRate, totalMonthDifference) - 1)));
        return Math.round(payment * 100) / 100.0f;
    }

    /**
     * Delegates to getLoanPayment for calculating the full monthly payment.
     *
     * @return The calculated monthly loan payment amount (rounded to two decimal places).
     * @see #getLoanPayment()
     */
    @Override
    public float getPayment() {
        return getLoanPayment();
    }
}
