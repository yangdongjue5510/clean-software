package part3.ch19;

import java.time.LocalDate;

public class ServiceCharge {
    private final LocalDate date;
    private final double amount;

    public ServiceCharge(final LocalDate date, final double amount) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isDate(final LocalDate date) {
        return this.date.isEqual(date);
    }
}
