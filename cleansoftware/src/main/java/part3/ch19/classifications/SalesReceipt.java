package part3.ch19.classifications;

import java.time.LocalDate;

public class SalesReceipt {
    private final LocalDate date;
    private final int amount;

    public SalesReceipt(final LocalDate date, final int amount) {
        this.date = date;
        this.amount = amount;
    }
}
