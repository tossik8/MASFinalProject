package nikita.toropov.masfinalproject.model.account;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import nikita.toropov.masfinalproject.model.PurchasedSecurity;
import nikita.toropov.masfinalproject.model.person.Client;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class InvestmentAccount extends Account{

    @NotBlank
    private String investmentObjective;

    @Builder
    public InvestmentAccount(Client owner, String investmentObjective){
        super(owner);
        setInvestmentObjective(investmentObjective);
    }

    @OneToMany(mappedBy = "investmentAccount", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PurchasedSecurity> purchasedSecurities;
}
