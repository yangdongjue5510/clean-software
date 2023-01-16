package part3.ch19;

import java.time.LocalDate;

public class MonthlySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDay(final LocalDate payDate) {
        return payDate.equals(payDate.withDayOfMonth(payDate.lengthOfMonth()));
    }
}
