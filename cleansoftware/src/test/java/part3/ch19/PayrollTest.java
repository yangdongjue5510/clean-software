package part3.ch19;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static part3.ch19.Application.GpayrollDatabase;

import java.time.LocalDate;
import java.util.List;
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
                () -> assertEquals(1000.00, schedule.getSalary()),
                () -> assertNotNull(classification),
                () -> assertNotNull(schedule),
                () -> assertNotNull(method)
        );
    }

    @Test
    @DisplayName("시간제 직원을 추가하는 기능을 테스트한다.")
    void addHourlyEmployee() {
        int empId = 1;
        AddHourlyEmployee transaction = new AddHourlyEmployee(empId, "Bob", "Home", 1000.00);
        transaction.execute();

        Employee employee = GpayrollDatabase.getEmployee(empId);
        HourlyPaymentClassification classification = (HourlyPaymentClassification) employee.getClassification();
        WeeklySchedule schedule = (WeeklySchedule) employee.getSchedule();
        HoldMethod method = employee.getMethod();

        assertAll(
                () -> assertEquals("Bob", employee.getName()),
                () -> assertNotNull(classification),
                () -> assertNotNull(schedule),
                () -> assertNotNull(method)
        );
    }

    @Test
    @DisplayName("수수료 받는 직원을 추가하는 기능을 테스트한다.")
    void addCommissionedEmployee() {
        int empId = 1;
        final AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Bob", "Home", 1000.00, 0.03);
        addCommissionedEmployee.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        CommissionedClassification classification = (CommissionedClassification) employee.getClassification();
        BiweeklySchedule schedule = (BiweeklySchedule) employee.getSchedule();
        final HoldMethod method = employee.getMethod();

        assertAll(
                () -> assertEquals("Bob", employee.getName()),
                () -> assertNotNull(classification),
                () -> assertNotNull(schedule),
                () -> assertNotNull(method)
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

    @Test
    @DisplayName("타임카드를 시간제 직원에 더하는 기능을 테스트한다.")
    void addTimeCardTransaction() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 1000.00);
        addHourlyEmployee.execute();

        TimeCardTransaction timeCardTransaction = new TimeCardTransaction(LocalDate.now(), 8.0, empId);
        timeCardTransaction.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        final PaymentClassification classification = employee.getClassification();
        final HourlyPaymentClassification hourlyPaymentClassification = (HourlyPaymentClassification) classification;

        final TimeCard timeCard = hourlyPaymentClassification.getTimeCard(LocalDate.now());
        assertThat(timeCard.getHours()).isEqualTo(8.0);
    }

    @Test
    @DisplayName("수수료를 받는 직원이 영수증을 기록하는 기능을 테스트한다.")
    void addSalesReceiptsTransaction() {
        int empId = 1;
        final AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Bob", "Home",
                1000.00, 0.01);
        addCommissionedEmployee.execute();

        SalesReceiptsTransaction salesReceiptsTransaction = new SalesReceiptsTransaction(empId, 1, LocalDate.now());
        salesReceiptsTransaction.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        final CommissionedClassification classification = (CommissionedClassification) employee.getClassification();
        List<SalesReceipt> salesReceipts =  classification.getReceipts();
        assertThat(salesReceipts.size()).isOne();
    }

    @Test
    @DisplayName("직원 이름 변경하는 기능을 테스트한다.")
    void changeEmployeeName() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 1000.00);
        addHourlyEmployee.execute();

        ChangeNameTransaction changeNameTransaction = new ChangeNameTransaction(empId, "Changed Name");
        changeNameTransaction.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        assertThat(employee.getName()).isEqualTo("Changed Name");
    }

    @Test
    @DisplayName("직원 주소 변경하는 기능 테스트")
    void changeEmployeeEmail() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 1000.00);
        addHourlyEmployee.execute();

        ChangeAddressTransaction changeEmailTransaction = new ChangeAddressTransaction(empId, "changedHome");
        changeEmailTransaction.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        assertThat(employee.getAddress()).isEqualTo("changedHome");
    }

    @Test
    @DisplayName("시급제로 임금 유형 변경하는 기능 테스트")
    void changeHourlyClassification() {
        int empId = 1;
        final AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 100000.00);
        salariedEmployee.execute();

        ChangeHourlyClassificationTransaction changeHourlyClassificationTransaction = new ChangeHourlyClassificationTransaction(
                empId, 1000.00);
        changeHourlyClassificationTransaction.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        assertAll(
                () -> assertThat(employee.getClassification()).isInstanceOf(HourlyPaymentClassification.class),
                () -> assertThat(((HourlyPaymentClassification) employee.getClassification()).getHourlyRate())
                        .isEqualTo(1000.00),
                () -> assertThat(employee.getSchedule()).isInstanceOf(WeeklySchedule.class)
        );
    }
}
