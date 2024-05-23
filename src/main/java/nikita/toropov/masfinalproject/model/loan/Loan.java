package nikita.toropov.masfinalproject.model.loan;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nikita.toropov.masfinalproject.model.person.Client;

import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@SuperBuilder
public abstract class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Min(0)
    private float interestRate;

    @Min(0)
    private float balance;

    @Column(nullable = false, updatable = false)
    private LocalDate openingDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Client owner;

    public Loan(Client owner, float balance, float interestRate) {
        setOwner(owner);
        setBalance(balance);
        setInterestRate(interestRate);
        openingDate = LocalDate.now();
    }

    public abstract float getPayment();
}
