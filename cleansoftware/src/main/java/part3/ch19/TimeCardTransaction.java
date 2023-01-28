package part3.ch19;

import static part3.ch19.application.Application.GpayrollDatabase;

import java.time.LocalDate;
import part3.ch19.classifications.HourlyPaymentClassification;
import part3.ch19.payroll_domain.Employee;
import part3.ch19.payroll_domain.Transaction;

public class TimeCardTransaction implements Transaction {
    private final LocalDate registerDate;
    private final double timeAmount;
    private final int empId;

    public TimeCardTransaction(final LocalDate registerDate, final double timeAmount, final int empId) {
        this.empId = empId;
        this.registerDate = registerDate;
        this.timeAmount = timeAmount;
    }

    @Override
    public void execute() {
        final Employee employee = GpayrollDatabase.getEmployee(empId);
        final HourlyPaymentClassification hourlyClassification = (HourlyPaymentClassification) employee.getClassification();
        hourlyClassification.addTimeCard(registerDate, timeAmount);
    }
}
