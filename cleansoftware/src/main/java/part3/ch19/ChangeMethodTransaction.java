package part3.ch19;

public abstract class ChangeMethodTransaction implements Transaction {
    protected final int empId;

    protected ChangeMethodTransaction(final int empId) {
        this.empId = empId;
    }
}
