package part3.ch19;

import static part3.ch19.Application.*;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
    protected ChangeClassificationTransaction(final int empId) {
        super(empId);
    }

    protected abstract PaymentClassification getClassification();
    protected abstract PaymentSchedule getSchedule();

    @Override
    public void execute() {
        final Employee employee = GpayrollDatabase.getEmployee(empId);
        employee.setClassification(getClassification());
        employee.setSchedule(getSchedule());
    }
}
