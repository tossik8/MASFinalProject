package nikita.toropov.masfinalproject.model.account;

import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private static Set<String> accountNumbers = new HashSet<>();

    @Column(unique = true, updatable = false)
    private String accountNumber;

    protected float balance;

    @Column(updatable = false)
    private LocalDate openingDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Client owner;

    public Account(Client owner){
        setOwner(owner);
        accountNumber = generateAccountNumber();
        openingDate = LocalDate.now();
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
