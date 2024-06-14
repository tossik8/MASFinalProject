package nikita.toropov.masfinalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import nikita.toropov.masfinalproject.model.person.Client;
import nikita.toropov.masfinalproject.model.person.employee.Employee;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @OneToMany(mappedBy = "registeredAt")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Client> clients;

    @OneToMany(mappedBy = "worksAt")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Employee> employees;

    @OneToMany(mappedBy = "branch")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Machine> machines;

    @Builder
    public Branch(String name, String address){
        setName(name);
        setAddress(address);
    }
}
