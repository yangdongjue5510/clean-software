package part3.ch19.payroll_domain;

import java.time.LocalDate;

public interface PaymentSchedule {
    boolean isPayDay(LocalDate payDate);
}
