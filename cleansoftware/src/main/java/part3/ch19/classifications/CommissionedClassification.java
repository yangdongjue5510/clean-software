package part3.ch19.classifications;

import java.util.ArrayList;
import java.util.List;
import part3.ch19.Paycheck;
import part3.ch19.payroll_domain.PaymentClassification;

public class CommissionedClassification implements PaymentClassification {
    private final double salary;
    private final double commissionRate;
    private final List<SalesReceipt> salesReceipts = new ArrayList<>();

    public CommissionedClassification(final double salary, final double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public List<SalesReceipt> getReceipts() {
        return salesReceipts;
    }

    public void addSalesReceipt(final SalesReceipt salesReceipt) {
        salesReceipts.add(salesReceipt);
    }

    @Override
    public double calculatePay(final Paycheck paycheck) {
        return 0;
    }
}
