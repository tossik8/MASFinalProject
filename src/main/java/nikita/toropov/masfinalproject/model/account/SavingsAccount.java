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

    private float interestRate;

    @Builder
    public SavingsAccount(Client owner){
        super(owner);
        setInterestRate();
    }

    @Override
    public void setBalance(float balance) {
        super.setBalance(balance);
        setInterestRate();
    }

    public void setInterestRate() {
        if(this.getBalance() <= 1000){
            this.interestRate = 1;
        }
        else if(this.getBalance() <= 2000){
            this.interestRate = 1.5f;
        }
        else{
            this.interestRate = 2;
        }
    }
}
