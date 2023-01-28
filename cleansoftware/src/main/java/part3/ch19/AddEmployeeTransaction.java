package part3.ch19;

import part3.ch19.payroll_domain.PaymentClassification;
import part3.ch19.payroll_domain.PaymentSchedule;
import part3.ch19.payroll_domain.Transaction;

public abstract class AddEmployeeTransaction implements Transaction {
    protected final int empId;
    protected final String address;
    protected final String name;

    protected AddEmployeeTransaction(final int empId, final String address, final String name) {
        this.empId = empId;
        this.address = address;
        this.name = name;
    }

    public abstract PaymentClassification getClassification();
    public abstract PaymentSchedule getSchedule();
}
