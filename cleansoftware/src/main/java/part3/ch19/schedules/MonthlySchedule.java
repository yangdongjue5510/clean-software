package part3.ch19.schedules;

import java.time.LocalDate;
import part3.ch19.payroll_domain.PaymentSchedule;

public class MonthlySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDay(final LocalDate payDate) {
        return payDate.equals(payDate.withDayOfMonth(payDate.lengthOfMonth()));
    }
}
