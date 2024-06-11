package nikita.toropov.masfinalproject.model.person.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.person.Credentials;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Intern extends Employee {

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "mentor_id")
    private FullTimeEmployee mentor;

    @Builder
    public Intern(String name, String surname, int salary, Credentials credentials, Branch worksAt, FullTimeEmployee mentor) {
        super(name, surname, salary, credentials, worksAt);
        setMentor(mentor);
    }

    /**
     * Alternative constructor for Intern objects.
     *
     * @param fullTimeEmployee The full-time employee serving as the base for the intern information.
     * @param salary The intern's salary.
     * @param mentor The intern's assigned mentor (a FullTimeEmployee object).
     */
    public Intern(Employee fullTimeEmployee, int salary, FullTimeEmployee mentor){
        this(fullTimeEmployee.getName(), fullTimeEmployee.getSurname(), salary,
                fullTimeEmployee.getCredentials(), fullTimeEmployee.getWorksAt(), mentor);
    }
}
