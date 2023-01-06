package part3.ch19;

import static part3.ch19.Application.GpayrollDatabase;

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
