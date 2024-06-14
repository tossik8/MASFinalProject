package nikita.toropov.masfinalproject.model.person.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.person.Credentials;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class FullTimeEmployee extends Employee {

    @OneToMany(mappedBy = "mentor")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Intern> mentees;

    @Builder
    public FullTimeEmployee(String name, String surname, int salary, Credentials credentials, Branch worksAt) {
        super(name, surname, salary, credentials, worksAt);
    }

    /**
     * Constructs a full-time employee object based on an existing employee's details and additional salary information.
     *
     * @param intern the existing employee from which to derive basic information.
     * @param salary the salary to set for the full-time employee.
     */
    public FullTimeEmployee(Employee intern, int salary){
        this(intern.getName(), intern.getSurname(), salary, intern.getCredentials(), intern.getWorksAt());
    }
}
