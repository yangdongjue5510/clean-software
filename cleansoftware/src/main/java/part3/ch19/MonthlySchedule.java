package part3.ch19;

public class MonthlySchedule implements PaymentSchedule {
    private final double salary;

    public MonthlySchedule(final double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
