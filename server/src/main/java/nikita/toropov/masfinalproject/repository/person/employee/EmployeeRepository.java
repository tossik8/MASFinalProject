package nikita.toropov.masfinalproject.repository.person.employee;

import nikita.toropov.masfinalproject.model.person.employee.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
