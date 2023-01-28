package part3.ch19.affiliations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import part3.ch19.Paycheck;
import part3.ch19.ServiceCharge;
import part3.ch19.payroll_domain.Affiliation;

public class UnionAffiliation implements Affiliation {
    private final List<ServiceCharge> serviceCharges;
    private final double unionFeeRate;

    public UnionAffiliation(final double unionFeeRate) {
        serviceCharges = new ArrayList<>();
        this.unionFeeRate = unionFeeRate;
    }

    public ServiceCharge getServiceCharge(final LocalDate date) {
        return serviceCharges.stream()
                .filter(it -> it.isDate(date))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당 날짜에 수수료 부과한 내역이 없음."));
    }

    public void addServiceCharge(final ServiceCharge serviceCharge) {
        serviceCharges.add(serviceCharge);
    }

    public double getFeeRate() {
       return unionFeeRate;
    }

    @Override
    public double calculateDeductions(final Paycheck paycheck) {
        int fridays = getNumbersOfFridays(paycheck.getDateOfPeriodStart(), paycheck.getPayDate());
        return paycheck.getGrossPay() * (unionFeeRate * fridays);
    }

    private int getNumbersOfFridays(final LocalDate start, final LocalDate end) {
        return (int) start.until(end, ChronoUnit.WEEKS) / 7;
    }
}
