package nikita.toropov.masfinalproject.model.account;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.*;
import nikita.toropov.masfinalproject.model.person.Client;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class SavingsAccount extends Account{

    @Getter
    @Setter
    @Min(0)
    private static int transferLimit = 6;

    @Min(0)
    private float interestRate;

    @Builder
    public SavingsAccount(Client owner, float interestRate){
        super(owner);
        setInterestRate(interestRate);
    }
}
