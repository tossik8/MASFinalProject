package nikita.toropov.masfinalproject.repository.person.employee;

import nikita.toropov.masfinalproject.model.person.employee.FullTimeEmployee;
import org.springframework.data.repository.CrudRepository;

public interface FullTimeEmployeeRepository extends CrudRepository<FullTimeEmployee, Long> {
}
