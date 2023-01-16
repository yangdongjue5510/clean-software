package part3.ch19;

import java.time.LocalDate;

public interface PaymentSchedule {
    boolean isPayDay(LocalDate payDate);
}
