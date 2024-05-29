package nikita.toropov.masfinalproject.model.loan;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Car extends Collateral{

    @NotBlank
    private String make, model;

    @Builder
    public Car(int price, int yearBuilt, String make, String model){
        super(price, yearBuilt);
        setMake(make);
        setModel(model);
    }
}
