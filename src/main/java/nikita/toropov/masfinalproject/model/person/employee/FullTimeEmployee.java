package nikita.toropov.masfinalproject.model.person.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Collections;
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
    public FullTimeEmployee(String name, String surname, int salary) {
        super(name, surname, salary);
    }

    public FullTimeEmployee(Employee intern, int salary){
        this(intern.getName(), intern.getSurname(), salary);
    }
}
