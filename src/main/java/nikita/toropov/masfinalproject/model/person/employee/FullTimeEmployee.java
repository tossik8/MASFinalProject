package nikita.toropov.masfinalproject.model.person.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import nikita.toropov.masfinalproject.model.Branch;

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
    public FullTimeEmployee(String name, String surname, int salary, Branch worksAt) {
        super(name, surname, salary, worksAt);
    }

    public FullTimeEmployee(Employee intern, int salary){
        this(intern.getName(), intern.getSurname(), salary, intern.getWorksAt());
    }
}
