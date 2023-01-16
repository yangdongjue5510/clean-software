package part3.ch19;

import java.time.LocalDate;
import java.util.Map;

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
        return timeCards.values().stream()
                .mapToDouble(this::calculatePerHour)
                .sum();
    }

    private double calculatePerHour(final TimeCard timeCard) {
        final double hours = timeCard.getHours();
        if (hours >= 0 && hours <= 8.0) {
            return hours * hourlyRate;
        }
        return (hourlyRate * 8) + (hours - 8) * 1.5 * hourlyRate;
    }
}
