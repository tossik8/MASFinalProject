package nikita.toropov.masfinalproject.model.person;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.account.Account;
import nikita.toropov.masfinalproject.model.loan.Loan;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Embedded
    @Valid
    @NotNull
    private Credentials credentials;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Branch registeredAt;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private Set<Account> accounts;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private Set<Loan> loans;

    @Builder
    public Client(String name, String surname, Credentials credentials, Branch registeredAt){
        setName(name);
        setSurname(surname);
        setCredentials(credentials);
        setRegisteredAt(registeredAt);
    }

    /**
     * Retrieves a list of all accounts, sorted by opening date in ascending order.
     *
     * @return A list of accounts sorted by their opening date (ascending order).
     */
    public List<Account> getAccounts() {
        return accounts.stream()
                .sorted((o1, o2) -> o1.getOpeningDate().isBefore(o2.getOpeningDate()) ? -1 :
                        o1.getOpeningDate().isAfter(o2.getOpeningDate()) ? 1 : 0)
                .collect(Collectors.toList());
    }
}
