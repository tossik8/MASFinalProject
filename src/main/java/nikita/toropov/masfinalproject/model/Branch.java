package nikita.toropov.masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import nikita.toropov.masfinalproject.model.person.Client;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @OneToMany(mappedBy = "registeredAt")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Client> clients;
}
