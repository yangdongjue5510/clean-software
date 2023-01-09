package part3.ch19;

import static part3.ch19.Application.GpayrollDatabase;

import java.time.LocalDate;

public class ServiceChargeTransaction implements Transaction {
    private final int memberId;
    private final LocalDate date;
    private final double amount;

    public ServiceChargeTransaction(final int memberId, final LocalDate date, final double amount) {
        this.memberId = memberId;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public void execute() {
        final Employee member = GpayrollDatabase.getUnionMember(memberId);
        final UnionAffiliation union = (UnionAffiliation) member.getAffiliation();
        union.addServiceCharge(new ServiceCharge(date, amount));
    }
}
