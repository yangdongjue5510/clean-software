package part3.ch19;

import java.time.LocalDate;

public class WeeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(final LocalDate payDate) {
        return false;
    }
}
