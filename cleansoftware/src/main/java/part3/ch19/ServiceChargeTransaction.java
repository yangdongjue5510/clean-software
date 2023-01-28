package part3.ch19;

import static part3.ch19.application.Application.GpayrollDatabase;

import java.time.LocalDate;
import part3.ch19.affiliations.UnionAffiliation;
import part3.ch19.payroll_domain.Employee;
import part3.ch19.payroll_domain.Transaction;

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
