package part3.ch19;

import static part3.ch19.Application.GpayrollDatabase;

import java.time.LocalDate;

public class SalesReceiptsTransaction implements Transaction {
    private final int empId;
    private final int amount;
    private final LocalDate date;

    public SalesReceiptsTransaction(final int empId, final int amount, final LocalDate date) {
        this.empId = empId;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public void execute() {
        final Employee employee = GpayrollDatabase.getEmployee(empId);
        final CommissionedClassification classification = (CommissionedClassification) employee.getClassification();
        classification.addSalesReceipt(new SalesReceipt(date, amount));
    }
}
