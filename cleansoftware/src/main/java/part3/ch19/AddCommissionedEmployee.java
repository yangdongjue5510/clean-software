package part3.ch19;

import part3.ch19.application.Application;
import part3.ch19.classifications.CommissionedClassification;
import part3.ch19.methods.HoldMethod;
import part3.ch19.payroll_domain.Employee;
import part3.ch19.payroll_domain.PaymentClassification;
import part3.ch19.payroll_domain.PaymentSchedule;
import part3.ch19.schedules.BiweeklySchedule;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
    private final double salary;
    private final double commissionRate;

    protected AddCommissionedEmployee(final int empId, final String name, final String address, final double salary,
                                      final double commissionRate) {
        super(empId, address, name);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    public PaymentClassification getClassification() {
       return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }

    @Override
    public void execute() {
        final Employee employee = new Employee(name, address);
        employee.setClassification(getClassification());
        employee.setSchedule(getSchedule());
        employee.setMethod(new HoldMethod(address));
        Application.GpayrollDatabase.addEmployee(empId, employee);
    }
}
