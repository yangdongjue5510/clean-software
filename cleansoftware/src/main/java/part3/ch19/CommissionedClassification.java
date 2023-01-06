package part3.ch19;

public class CommissionedClassification implements PaymentClassification {
    private final double salary;
    private final double commissionRate;

    public CommissionedClassification(final double salary, final double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }
}
