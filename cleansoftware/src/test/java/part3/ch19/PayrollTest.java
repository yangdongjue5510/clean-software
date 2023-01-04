package part3.ch19;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static part3.ch19.Application.GpayrollDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PayrollTest {
    @BeforeEach
    void setUp() {
        GpayrollDatabase.clear();
    }

    @Test
    @DisplayName("월급받는 직원을 추가하는 기능을 테스트한다.")
    void addSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee transaction = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        transaction.execute();

        Employee employee = GpayrollDatabase.getEmployee(empId);
        SalariedClassification classification = (SalariedClassification) employee.getClassification();
        MonthlySchedule schedule = (MonthlySchedule) employee.getSchedule();
        HoldMethod method = employee.getMethod();

        assertAll(
                () -> assertEquals("Bob", employee.getName()),
                () -> assertEquals(1000.00, schedule.getSalary())
        );
    }

    @Test
    @DisplayName("직원을 삭제하는 기능을 테스트한다.")
    void deleteEmployee() {
        int empId = 1;
        final AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();

        DeleteEmployee deleteEmployee = new DeleteEmployee(empId);
        deleteEmployee.execute();

        assertNull(GpayrollDatabase.getEmployee(empId));
    }
}
