package part3.ch19.methods;

import part3.ch19.payroll_domain.Transaction;

public abstract class ChangeMethodTransaction implements Transaction {
    protected final int empId;

    protected ChangeMethodTransaction(final int empId) {
        this.empId = empId;
    }
}
