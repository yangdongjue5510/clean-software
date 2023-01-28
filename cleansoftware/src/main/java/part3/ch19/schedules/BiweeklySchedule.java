package part3.ch19.schedules;

import java.time.LocalDate;
import part3.ch19.payroll_domain.PaymentSchedule;

public class BiweeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(final LocalDate payDate) {
        return false;
    }
}
