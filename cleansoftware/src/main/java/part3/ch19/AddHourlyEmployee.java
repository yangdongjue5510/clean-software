package part3.ch19;

import static part3.ch19.application.Application.GpayrollDatabase;

import java.util.HashMap;
import part3.ch19.classifications.HourlyPaymentClassification;
import part3.ch19.methods.HoldMethod;
import part3.ch19.payroll_domain.Employee;
import part3.ch19.payroll_domain.PaymentClassification;
import part3.ch19.payroll_domain.PaymentSchedule;
import part3.ch19.schedules.WeeklySchedule;

public class AddHourlyEmployee extends AddEmployeeTransaction {
    private final double hourlyRate;

    public AddHourlyEmployee(final int empId, final String name, final String address, final double hourlyRate) {
        super(empId, address, name);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyPaymentClassification(new HashMap<>(), hourlyRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }

    @Override
    public void execute() {
        final Employee employee = new Employee(name, address);
        employee.setClassification(getClassification());
        employee.setSchedule(getSchedule());
        employee.setMethod(new HoldMethod(address));
        GpayrollDatabase.addEmployee(empId, employee);
    }
}
