package part3.ch19;

public abstract class ChangeEmployeeTransaction implements Transaction {
    protected final int empId;

    protected ChangeEmployeeTransaction(final int empId) {
        this.empId = empId;
    }
}
