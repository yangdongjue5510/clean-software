package part3.ch19;

import java.util.HashMap;

public class ChangeHourlyClassificationTransaction extends ChangeClassificationTransaction {
    private final double hourlyRate;

    public ChangeHourlyClassificationTransaction(final int empId, final double hourlyRate) {
        super(empId);
        this.hourlyRate = hourlyRate;
    }


    @Override
    protected PaymentClassification getClassification() {
       return new HourlyPaymentClassification(new HashMap<>(), hourlyRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
