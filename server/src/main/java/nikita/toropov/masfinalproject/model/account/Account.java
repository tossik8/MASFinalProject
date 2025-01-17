package nikita.toropov.masfinalproject.model.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nikita.toropov.masfinalproject.model.person.Client;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder
public abstract class Account {

    public enum Status {ACTIVE, INACTIVE, FROZEN, CLOSED}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private static Set<String> accountNumbers = new HashSet<>();

    @Column(unique = true, updatable = false)
    private String accountNumber;

    protected float balance;

    @Column(updatable = false)
    private LocalDate openingDate;

    @NotNull
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Client owner;

    public Account(Client owner){
        setOwner(owner);
        accountNumber = generateAccountNumber();
        openingDate = LocalDate.now();
        status = Status.ACTIVE;
    }

    /**
     * Generates a unique 26-digit account number starting with "6110901014".
     *
     * @return a unique 26-digit account number.
     */
    private static String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append("6110901014");
        for (int i = 0; i < 16; i++) {
            accountNumber.append((int) (Math.random() * 10));
        }
        if(accountNumbers.contains(accountNumber.toString())){
            return generateAccountNumber();
        }
        return accountNumber.toString();
    }

    /**
     * Sets the balance of the account.
     *
     * @param balance the non-negative balance to set for the account.
     * @throws IllegalArgumentException if the specified {@code balance} is negative.
     */
    public void setBalance(float balance){
        if(balance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }
}
