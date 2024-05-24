package nikita.toropov.masfinalproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nikita.toropov.masfinalproject.model.Branch;
import nikita.toropov.masfinalproject.model.person.employee.Employee;
import nikita.toropov.masfinalproject.model.person.employee.FullTimeEmployee;
import nikita.toropov.masfinalproject.model.person.employee.Intern;
import nikita.toropov.masfinalproject.repository.person.employee.EmployeeRepository;
import nikita.toropov.masfinalproject.repository.person.employee.FullTimeEmployeeRepository;
import nikita.toropov.masfinalproject.repository.person.employee.InternRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InternRepository internRepository;

    @Autowired
    private FullTimeEmployeeRepository fullTimeEmployeeRepository;

    @Autowired
    private BranchRepository branchRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Branch branch;

    @BeforeEach
    public void setup(){
        branch = branchRepository.findById(1000L).orElseThrow();
    }

    @Test
    public void testCreateEmployee(){
        Employee employee = FullTimeEmployee.builder()
                .name("Mike")
                .surname("Geller")
                .salary(10000)
                .worksAt(branch)
                .build();
        employeeRepository.save(employee);
        entityManager.flush();

        assertEquals("Mike", employee.getName());
        assertEquals("Geller", employee.getSurname());
        assertEquals(10000, employee.getSalary());
        assertEquals(branch, employee.getWorksAt());
        assertTrue(branch.getEmployees().contains(employee));
        assertTrue(employeeRepository.existsById(employee.getId()));
    }

    @Test
    public void testCreateIntern(){
        Intern employee = Intern.builder()
                .name("Mike")
                .surname("Geller")
                .salary(5000)
                .worksAt(branch)
                .build();
        employeeRepository.save(employee);
        entityManager.flush();

        assertEquals("Mike", employee.getName());
        assertEquals("Geller", employee.getSurname());
        assertEquals(5000, employee.getSalary());
        assertEquals(branch, employee.getWorksAt());
        assertTrue(branch.getEmployees().contains(employee));
        assertNull(employee.getMentor());
        assertTrue(employeeRepository.existsById(employee.getId()));
    }

    @Test
    public void testChangeEmployeeToIntern(){
        Employee employee = FullTimeEmployee.builder()
                .name("Mike")
                .surname("Geller")
                .salary(10000)
                .worksAt(branch)
                .build();
        employeeRepository.save(employee);
        entityManager.flush();

        Intern intern = new Intern(employee, 4000, null);
        employeeRepository.save(intern);
        employeeRepository.delete(employee);
        entityManager.flush();

        assertTrue(employeeRepository.existsById(intern.getId()));
        assertFalse(employeeRepository.existsById(employee.getId()));
        assertEquals("Mike", intern.getName());
        assertEquals("Geller", intern.getSurname());
        assertEquals(4000, intern.getSalary());
        assertEquals(branch, intern.getWorksAt());
        assertTrue(branch.getEmployees().contains(intern));
        assertFalse(branch.getEmployees().contains(employee));
    }

    @Test
    public void testChangeInternToEmployee(){
        Employee intern = Intern.builder()
                .name("Mike")
                .surname("Geller")
                .mentor(null)
                .salary(4000)
                .worksAt(branch)
                .build();
        employeeRepository.save(intern);
        entityManager.flush();

        Employee employee = new FullTimeEmployee(intern, 10000);
        employeeRepository.save(employee);
        employeeRepository.delete(intern);
        entityManager.flush();

        assertTrue(employeeRepository.existsById(employee.getId()));
        assertFalse(employeeRepository.existsById(intern.getId()));
        assertEquals("Mike", employee.getName());
        assertEquals("Geller", employee.getSurname());
        assertEquals(10000, employee.getSalary());
        assertEquals(branch, employee.getWorksAt());
        assertTrue(branch.getEmployees().contains(employee));
        assertFalse(branch.getEmployees().contains(intern));
    }

    @Test
    public void testRemoveEmployee(){
        FullTimeEmployee employee = FullTimeEmployee.builder()
                .name("Mike")
                .surname("Geller")
                .salary(10000)
                .worksAt(branch)
                .build();
        Intern intern = Intern.builder()
                .name("Mike")
                .surname("Geller")
                .mentor(employee)
                .salary(4000)
                .worksAt(branch)
                .build();
        Intern intern2 = Intern.builder()
                .name("Mike")
                .surname("Geller")
                .mentor(employee)
                .salary(4000)
                .worksAt(branch)
                .build();
        employeeRepository.saveAll(List.of(employee, intern, intern2));
        entityManager.flush();
        entityManager.refresh(employee);

        assertEquals(employee, intern.getMentor());
        assertEquals(employee, intern2.getMentor());
        assertTrue(employee.getMentees().containsAll(Set.of(intern, intern2)));
        assertTrue(branch.getEmployees().contains(employee));

        employeeRepository.delete(employee);
        entityManager.flush();
        entityManager.refresh(intern);
        entityManager.refresh(intern2);
        entityManager.refresh(branch);

        assertFalse(employeeRepository.existsById(employee.getId()));
        assertNull(intern.getMentor());
        assertNull(intern2.getMentor());
        assertFalse(branch.getEmployees().contains(employee));
    }

    @Test
    public void testDeleteIntern(){
        FullTimeEmployee employee = FullTimeEmployee.builder()
                .name("Mike")
                .surname("Geller")
                .salary(10000)
                .worksAt(branch)
                .build();
        Intern intern = Intern.builder()
                .name("Mike")
                .surname("Geller")
                .mentor(employee)
                .salary(4000)
                .worksAt(branch)
                .build();
        Intern intern2 = Intern.builder()
                .name("Mike")
                .surname("Geller")
                .mentor(employee)
                .salary(4000)
                .worksAt(branch)
                .build();
        employeeRepository.saveAll(List.of(employee, intern, intern2));
        entityManager.flush();
        entityManager.refresh(employee);

        assertEquals(employee, intern.getMentor());
        assertTrue(employee.getMentees().contains(intern));
        assertTrue(branch.getEmployees().contains(intern));

        employeeRepository.delete(intern);
        entityManager.flush();
        entityManager.refresh(employee);
        entityManager.refresh(branch);

        assertFalse(employeeRepository.existsById(intern.getId()));
        assertFalse(employee.getMentees().contains(intern));
        assertFalse(branch.getEmployees().contains(intern));
    }

    @Test
    public void testAddMentee(){
        FullTimeEmployee employee = FullTimeEmployee.builder()
                .name("Mike")
                .surname("Geller")
                .salary(10000)
                .worksAt(branch)
                .build();
        Intern intern = Intern.builder()
                .name("Mike")
                .surname("Geller")
                .mentor(null)
                .salary(4000)
                .worksAt(branch)
                .build();
        employeeRepository.saveAll(Set.of(employee, intern));
        entityManager.flush();
        entityManager.refresh(employee);

        employee.getMentees().add(intern);
        intern.setMentor(employee);

        employeeRepository.saveAll(Set.of(employee, intern));
        entityManager.flush();
        entityManager.refresh(employee);
        entityManager.refresh(intern);

        assertTrue(employee.getMentees().contains(intern));
        assertEquals(employee, intern.getMentor());
    }
}
