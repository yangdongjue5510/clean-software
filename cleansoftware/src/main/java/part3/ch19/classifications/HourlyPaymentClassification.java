package part3.ch19.classifications;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Map.Entry;
import part3.ch19.Paycheck;
import part3.ch19.payroll_domain.PaymentClassification;

public class HourlyPaymentClassification implements PaymentClassification {
    private final Map<LocalDate, TimeCard> timeCards;
    private final double hourlyRate;

    public HourlyPaymentClassification(final Map<LocalDate, TimeCard> timeCards, final double hourlyRate) {
        this.timeCards = timeCards;
        this.hourlyRate = hourlyRate;
    }

    public TimeCard getTimeCard(final LocalDate date) {
        return timeCards.get(date);
    }

    public void addTimeCard(final LocalDate registerDate, final double timeAmount) {
        timeCards.put(registerDate, new TimeCard(timeAmount));
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public double calculatePay(final Paycheck paycheck) {
        final LocalDate payDate = paycheck.getPayDate();
        return timeCards.entrySet().stream()
                .filter(entry -> isInSameWeek(payDate, entry))
                .mapToDouble(entry -> calculatePerHour(entry.getValue()))
                .sum();
    }

    private boolean isInSameWeek(final LocalDate payDate, final Entry<LocalDate, TimeCard> entry) {
        return ChronoUnit.WEEKS.between(payDate, entry.getKey()) == 0;
    }

    private double calculatePerHour(final TimeCard timeCard) {
        final double hours = timeCard.getHours();
        if (hours >= 0 && hours <= 8.0) {
            return hours * hourlyRate;
        }
        return (hourlyRate * 8) + (hours - 8) * 1.5 * hourlyRate;
    }
}
