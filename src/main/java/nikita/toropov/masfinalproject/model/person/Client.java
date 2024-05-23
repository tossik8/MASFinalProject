package nikita.toropov.masfinalproject.model.person;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.account.Account;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String name, surname;

    @NotNull
    @Embedded
    private Credentials credentials;

    @ManyToOne(optional = false)
    @JoinColumn(name = "branch_id", nullable = false, updatable = false)
    private Branch registeredAt;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private Set<Account> accounts;

    @Builder
    public Client(String name, String surname, Credentials credentials, Branch registeredAt){
        setName(name);
        setSurname(surname);
        setCredentials(credentials);
        setRegisteredAt(registeredAt);
        accounts = new HashSet<>();
    }

    public List<Account> getAccounts() {
        return accounts.stream()
                .sorted((o1, o2) -> o1.getOpeningDate().isBefore(o2.getOpeningDate()) ? -1 :
                        o1.getOpeningDate().isAfter(o2.getOpeningDate()) ? 1 : 0)
                .collect(Collectors.toList());
    }
}
