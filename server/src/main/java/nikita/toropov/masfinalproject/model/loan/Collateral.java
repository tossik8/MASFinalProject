package nikita.toropov.masfinalproject.model.loan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Collateral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Min(0)
    private int price;

    @Min(0)
    private int yearBuilt;

    @OneToOne(mappedBy = "collateral")
    @JoinColumn(name = "loan_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Loan loan;

    public Collateral(int price, int yearBuilt){
        setPrice(price);
        setYearBuilt(yearBuilt);
    }
}
