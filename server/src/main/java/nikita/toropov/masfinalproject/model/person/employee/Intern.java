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
     * Constructs an intern object based on an existing full-time employee's details, additional salary information, and a mentor.
     *
     * @param fullTimeEmployee the existing full-time employee from which to derive basic information.
     * @param salary the salary to set for the intern.
     * @param mentor the mentor assigned to the intern.
     */
    public Intern(Employee fullTimeEmployee, int salary, FullTimeEmployee mentor){
        this(fullTimeEmployee.getName(), fullTimeEmployee.getSurname(), salary,
                fullTimeEmployee.getCredentials(), fullTimeEmployee.getWorksAt(), mentor);
    }
}
