package part3.ch19;

import part3.ch19.payroll_domain.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {
    protected final int empId;

    protected ChangeEmployeeTransaction(final int empId) {
        this.empId = empId;
    }
}
