package part3.ch19;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Paycheck {

    private final LocalDate dateOfPeriodStart;
    private final LocalDate payDate;
    private double grossPay;
    private double deductions;
    private double netPay;
    private final Map<String, String> fields = new HashMap<>();

    public Paycheck(final LocalDate dateOfPeriodStart, final LocalDate payDate) {
        this.payDate = payDate;
        this.dateOfPeriodStart = dateOfPeriodStart;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setField(final String fieldName, final String value) {
        fields.put(fieldName, value);
    }

    public String getFields(final String fieldName) {
        return fields.get(fieldName);
    }

    public double getNetPay() {
        return netPay;
    }

    public void setGrossPay(final double grossPay) {
        this.grossPay = grossPay;
    }

    public void setDeductions(final double deductions) {
        this.deductions = deductions;
    }

    public void setNetPay(final double netPay) {
        this.netPay = netPay;
    }

    public LocalDate getDateOfPeriodStart() {
        return dateOfPeriodStart;
    }
}
