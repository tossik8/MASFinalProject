package nikita.toropov.masfinalproject.model;

import nikita.toropov.masfinalproject.model.person.employee.FullTimeEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeTest {


    Branch branch;

    @BeforeEach
    public void setup(){
        branch = Branch.builder()
                .name("B")
                .address("Koszykowa 86")
                .build();
    }

    @Test
    public void testChangeSalary(){
        FullTimeEmployee employee = FullTimeEmployee.builder()
                .name("Mike")
                .surname("Geller")
                .salary(0)
                .worksAt(branch)
                .build();

        assertEquals(0, employee.getSalary());

        employee.setSalary(10000);

        assertEquals(10000, employee.getSalary());
        assertThrows(IllegalStateException.class, () -> employee.setSalary(15001));
        assertThrows(IllegalStateException.class, () -> employee.setSalary(4999));

        employee.setSalary(7000);

        assertEquals(7000, employee.getSalary());

        employee.setSalary(10500);

        assertEquals(10500, employee.getSalary());
    }
}
