package part3.ch19;

import static part3.ch19.Application.GpayrollDatabase;

public class AddSalariedEmployee extends AddEmployeeTransaction {
    private final double salary;

    public AddSalariedEmployee(final int empId, final String name, final String address, final double salary) {
        super(empId, address, name);
        this.salary = salary;
    }

    @Override
    public PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
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
