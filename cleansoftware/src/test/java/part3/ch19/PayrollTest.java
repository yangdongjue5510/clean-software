package part3.ch19;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static part3.ch19.application.Application.GpayrollDatabase;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import part3.ch19.affiliations.ChangeAffiliationTransaction;
import part3.ch19.affiliations.ChangeNoAffiliationTransaction;
import part3.ch19.affiliations.ChangeUnionAffiliationTransaction;
import part3.ch19.affiliations.UnionAffiliation;
import part3.ch19.classifications.ChangeHourlyClassificationTransaction;
import part3.ch19.classifications.CommissionedClassification;
import part3.ch19.classifications.HourlyPaymentClassification;
import part3.ch19.classifications.SalariedClassification;
import part3.ch19.classifications.SalesReceipt;
import part3.ch19.classifications.TimeCard;
import part3.ch19.methods.ChangeDirectMethodTransaction;
import part3.ch19.methods.ChangeMethodTransaction;
import part3.ch19.methods.DirectMethod;
import part3.ch19.payroll_domain.Employee;
import part3.ch19.payroll_domain.PaymentClassification;
import part3.ch19.payroll_domain.PaymentMethod;
import part3.ch19.schedules.BiweeklySchedule;
import part3.ch19.schedules.MonthlySchedule;
import part3.ch19.schedules.WeeklySchedule;

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
        PaymentMethod method = employee.getMethod();

        assertAll(
                () -> assertEquals("Bob", employee.getName()),
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
        PaymentMethod method = employee.getMethod();

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
        final AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Bob", "Home",
                1000.00, 0.03);
        addCommissionedEmployee.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        CommissionedClassification classification = (CommissionedClassification) employee.getClassification();
        BiweeklySchedule schedule = (BiweeklySchedule) employee.getSchedule();
        final PaymentMethod method = employee.getMethod();

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
        List<SalesReceipt> salesReceipts = classification.getReceipts();
        assertThat(salesReceipts.size()).isOne();
    }

    @Test
    @DisplayName("조합 비용을 지불하는 지 테스트한다.")
    void addServiceChange() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 100.00);
        addHourlyEmployee.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        UnionAffiliation affiliation = new UnionAffiliation(0.01);
        employee.setAffiliation(affiliation);

        int memberId = 2;
        GpayrollDatabase.addUnionMember(memberId, employee);
        ServiceChargeTransaction serviceChargeTransaction = new ServiceChargeTransaction(memberId, LocalDate.now(),
                12.95);
        serviceChargeTransaction.execute();

        ServiceCharge serviceCharge = affiliation.getServiceCharge(LocalDate.now());
        assertThat(serviceCharge.getAmount()).isEqualTo(12.95);
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

    @Test
    @DisplayName("급여 지급 방법을 직접 수령으로 변경하는 기능 테스트")
    void changeDirectMethodTransaction() {
        int empId = 1;
        final AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 10000.00);
        salariedEmployee.execute();

        ChangeMethodTransaction changeMethodTransaction = new ChangeDirectMethodTransaction(empId, "Woori", "1234");
        changeMethodTransaction.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        assertThat(employee.getMethod()).isInstanceOf(DirectMethod.class);
    }

    @Test
    @DisplayName("직원이 조합에 가입하도록 변경하는 기능 테스트")
    void changeUnionAffiliationTransaction() {
        int empId = 1;
        int memberId = 2;
        final AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        salariedEmployee.execute();

        ChangeAffiliationTransaction changeAffiliationTransaction = new ChangeUnionAffiliationTransaction(empId,
                memberId, 0.05);
        changeAffiliationTransaction.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        final Employee member = GpayrollDatabase.getUnionMember(memberId);

        final UnionAffiliation union = (UnionAffiliation) employee.getAffiliation();
        assertAll(
                () -> assertThat(union).isNotNull(),
                () -> assertThat(union.getFeeRate()).isEqualTo(0.05),
                () -> assertThat(member).isNotNull()
        );
    }

    @Test
    @DisplayName("직원이 조합에서 탈퇴하도록 수정하는 기능 테스트")
    void changeNoAffiliationTransaction() {
        int empId = 1;
        final AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Bob", "Home",
                10000.00, 0.05);
        addCommissionedEmployee.execute();

        final Employee employee = GpayrollDatabase.getEmployee(empId);
        UnionAffiliation affiliation = new UnionAffiliation(0.01);
        employee.setAffiliation(affiliation);

        int memberId = 2;
        GpayrollDatabase.addUnionMember(memberId, employee);

        ChangeNoAffiliationTransaction changeNoAffiliationTransaction = new ChangeNoAffiliationTransaction(empId,
                memberId);
        changeNoAffiliationTransaction.execute();

        assertThat(employee.getAffiliation()).isNotNull();
    }

    @Test
    @DisplayName("월급 받는 직원에게 정상적인 날짜에 임금 지급하는 기능")
    void paySingleSalariedEmployee() {
        int empId = 1;
        final AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 10000.00);
        addSalariedEmployee.execute();

        final LocalDate payDate = LocalDate.of(2023, 1, 31);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertAll(
                () -> assertThat(paycheck.getPayDate()).isEqualTo(payDate),
                () -> assertThat(paycheck.getGrossPay()).isEqualTo(10000.00),
                () -> assertThat(paycheck.getDeductions()).isEqualTo(0.0),
                () -> assertThat(paycheck.getFields("Disposition")).isEqualTo("Hold"),
                () -> assertThat(paycheck.getNetPay()).isEqualTo(10000.00)
        );
    }

    @Test
    @DisplayName("타임카드가 없는 시간제 직원에게 0원 임금 지급하는 기능")
    void paySingleHourlyEmployeeNoTimeCard() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 15.25);
        addHourlyEmployee.execute();

        final LocalDate fridayDate = LocalDate.of(2021, 12, 24);
        final PaydayTransaction paydayTransaction = new PaydayTransaction(fridayDate);
        paydayTransaction.execute();
        validateHourlyPaycheck(paydayTransaction, empId, fridayDate, 0);
    }

    void validateHourlyPaycheck(PaydayTransaction paydayTransaction, int empId, LocalDate payDate, double pay) {
        final Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertAll(
                () -> assertThat(paycheck.getPayDate()).isEqualTo(payDate),
                () -> assertThat(paycheck.getGrossPay()).isEqualTo(pay),
                () -> assertThat(paycheck.getDeductions()).isEqualTo(0.0),
                () -> assertThat(paycheck.getNetPay()).isEqualTo(pay)
        );
    }

    @Test
    @DisplayName("타임카드가 하나 있는 시간제 직원에게 임금 지급하는 기능")
    void paySingleHourlyEmployeeOneTimeCard() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 15.25);
        addHourlyEmployee.execute();
        final LocalDate fridayDate = LocalDate.of(2021, 12, 24);

        final TimeCardTransaction timeCardTransaction = new TimeCardTransaction(fridayDate, 2.0, empId);
        timeCardTransaction.execute();
        final PaydayTransaction paydayTransaction = new PaydayTransaction(fridayDate);
        paydayTransaction.execute();
        validateHourlyPaycheck(paydayTransaction, empId, fridayDate, 30.5);
    }

    @Test
    @DisplayName("타임카드가 하나 있고 초과 시간 근무한 시간제 직원에게 임금 지급하는 기능")
    void paySingleHourlyEmployeeOneTimeCardWithOverTime() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 15.25);
        addHourlyEmployee.execute();
        final LocalDate fridayDate = LocalDate.of(2021, 12, 24);

        final TimeCardTransaction timeCardTransaction = new TimeCardTransaction(fridayDate, 9.0, empId);
        timeCardTransaction.execute();
        final PaydayTransaction paydayTransaction = new PaydayTransaction(fridayDate);
        paydayTransaction.execute();
        validateHourlyPaycheck(paydayTransaction, empId, fridayDate, (8 + 1.5) * 15.25);
    }

    @Test
    @DisplayName("금요일이 아닌 날짜에 임금 지급을 요구하면 수표가 Null 반환하는 기능")
    void paySingleHourlyEmployeeWrongDate() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 15.25);
        addHourlyEmployee.execute();
        final LocalDate thursdayDate = LocalDate.of(2021, 12, 23);

        final PaydayTransaction paydayTransaction = new PaydayTransaction(thursdayDate);
        paydayTransaction.execute();

        assertNull(paydayTransaction.getPaycheck(empId));
    }

    @Test
    @DisplayName("타임카드가 두개 있는 시간제 직원에게 임금 지급하는 기능")
    void paySingleHourlyEmployeeTwoTimeCard() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 15.25);
        addHourlyEmployee.execute();
        final LocalDate thursdayDate = LocalDate.of(2021, 12, 23);
        final LocalDate fridayDate = LocalDate.of(2021, 12, 24);

        final TimeCardTransaction firstTimeCardTransaction = new TimeCardTransaction(thursdayDate, 2.0, empId);
        firstTimeCardTransaction.execute();
        final TimeCardTransaction secondTimeCardTransaction = new TimeCardTransaction(fridayDate, 3.0, empId);
        secondTimeCardTransaction.execute();

        final PaydayTransaction paydayTransaction = new PaydayTransaction(fridayDate);
        paydayTransaction.execute();
        validateHourlyPaycheck(paydayTransaction, empId, fridayDate, 15.25 * 5);
    }

    @Test
    @DisplayName("타임카드가 현재 지금 주기인 경우만 임금 지급한다.")
    void paySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 15.25);
        addHourlyEmployee.execute();
        final LocalDate validPeriodDate = LocalDate.of(2021, 12, 24);
        final LocalDate invalidPeriodDate = LocalDate.of(2021, 12, 17);

        final TimeCardTransaction firstTimeCardTransaction = new TimeCardTransaction(validPeriodDate, 2.0, empId);
        firstTimeCardTransaction.execute();
        final TimeCardTransaction secondTimeCardTransaction = new TimeCardTransaction(invalidPeriodDate, 3.0, empId);
        secondTimeCardTransaction.execute();

        final PaydayTransaction paydayTransaction = new PaydayTransaction(validPeriodDate);
        paydayTransaction.execute();
        validateHourlyPaycheck(paydayTransaction, empId, validPeriodDate, 15.25 * 2);
    }

    @Test
    @DisplayName("조합에 속한 직원이 공제된 임금을 지급한다.")
    void salariedUnionMemberDues() {
        int empId = 1;
        final AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bob", "Home", 15.24);
        addHourlyEmployee.execute();

        int memberId = Integer.MAX_VALUE;
        final ChangeUnionAffiliationTransaction changeUnionAffiliationTransaction = new ChangeUnionAffiliationTransaction(
                empId, memberId, 9.42);
        changeUnionAffiliationTransaction.execute();
        final LocalDate fridayDate = LocalDate.of(2021, 12, 24);

        final TimeCardTransaction firstTimeCardTransaction = new TimeCardTransaction(fridayDate, 2.0, empId);
        firstTimeCardTransaction.execute();
        final PaydayTransaction paydayTransaction = new PaydayTransaction(fridayDate);
        paydayTransaction.execute();
        final Paycheck paycheck = paydayTransaction.getPaycheck(empId);
        assertAll(
                () -> assertSame(paycheck.getPayDate(), fridayDate),
                () -> assertThat(paycheck.getGrossPay()).isEqualTo(15.24 * 2)
        );
    }
}
