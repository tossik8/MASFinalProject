package nikita.toropov.masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 5)
    private String code;

    @OneToMany(mappedBy = "security", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<PurchasedSecurity> purchasedSecurities;

    @Builder
    public Security(String name, String code){
        setName(name);
        setCode(code);
    }
}
