package nikita.toropov.masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nikita.toropov.masfinalproject.model.account.InvestmentAccount;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchasedSecurity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "security_id", nullable = false, updatable = false)
    private Security security;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private InvestmentAccount investmentAccount;

    @NotNull
    private LocalDateTime date;

    @Min(0)
    private float price;

    @Min(1)
    private int quantity;
}
