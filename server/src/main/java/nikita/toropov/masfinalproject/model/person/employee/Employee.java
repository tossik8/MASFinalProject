package nikita.toropov.masfinalproject.model.person.employee;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.person.Credentials;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String name, surname;

    @Min(0)
    private int salary;

    @Embedded
    @Valid
    @NotNull
    private Credentials credentials;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Branch worksAt;

    public Employee(String name, String surname, int salary, Credentials credentials, Branch worksAt) {
        setName(name);
        setSurname(surname);
        setSalary(salary);
        setCredentials(credentials);
        setWorksAt(worksAt);
    }

    /**
     * Updates the employee's salary, enforcing restrictions on salary changes.
     *
     * @param salary The new salary for the employee.
     * @throws IllegalStateException if the salary change is outside the allowed range (less than 50% decrease or more than 50% increase).
     */
    public void setSalary(int salary) {
        if (this.salary != 0) {
            float change = (float) salary / this.salary;
            if (change < 0.5 || change > 1.5) {
                throw new IllegalStateException("The salary is not allowed to change by this much");
            }
        }
        this.salary = salary;
    }
}
