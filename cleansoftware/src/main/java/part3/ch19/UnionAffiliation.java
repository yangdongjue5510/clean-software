package part3.ch19;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
