package part3.ch19;

public class SalariedClassification implements PaymentClassification{
   private final double salary;

    public SalariedClassification(final double salary) {
        this.salary = salary;
    }

    @Override
    public double calculatePay(final Paycheck paycheck) {
       return salary;
    }
}
