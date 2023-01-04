package part3.ch19;

import static part3.ch19.Application.*;

public class DeleteEmployee implements Transaction {
    private final int empId;

    public DeleteEmployee(final int empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        GpayrollDatabase.deleteEmployee(empId);
    }
}
