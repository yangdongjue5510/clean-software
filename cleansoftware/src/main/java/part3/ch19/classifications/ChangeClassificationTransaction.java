package part3.ch19.classifications;

import static part3.ch19.application.Application.*;

import part3.ch19.ChangeEmployeeTransaction;
import part3.ch19.payroll_domain.Employee;
import part3.ch19.payroll_domain.PaymentClassification;
import part3.ch19.payroll_domain.PaymentSchedule;

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
