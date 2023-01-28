package part3.ch19;

import static part3.ch19.application.Application.*;

import part3.ch19.payroll_domain.Transaction;

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
