package part3.ch19;

import static part3.ch19.application.Application.GpayrollDatabase;

import part3.ch19.payroll_domain.Employee;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private final String name;

    public ChangeNameTransaction(final int empId, final String name) {
        super(empId);
        this.name = name;
    }

    @Override
    public void execute() {
        final Employee employee = GpayrollDatabase.getEmployee(empId);
        employee.setName(name);
    }
}
