package nikita.toropov.masfinalproject.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Client owner;

    public Account(Client owner){
        setOwner(owner);
        accountNumber = generateAccountNumber();
        openingDate = LocalDate.now();
        status = Status.ACTIVE;
    }

    private static String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append("61 1090 1014");
        for (int i = 0; i < 16; i++) {
            if(i % 4 == 0){
                accountNumber.append(' ');
            }
            accountNumber.append((int) (Math.random() * 10));
        }
        if(accountNumbers.contains(accountNumber.toString())){
            return generateAccountNumber();
        }
        return accountNumber.toString();
    }

    public void setBalance(float balance){
        if(balance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }
}
