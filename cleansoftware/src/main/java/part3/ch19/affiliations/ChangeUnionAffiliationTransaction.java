package part3.ch19.affiliations;

import static part3.ch19.application.Application.GpayrollDatabase;

import part3.ch19.payroll_domain.Affiliation;
import part3.ch19.payroll_domain.Employee;

public class ChangeUnionAffiliationTransaction extends ChangeAffiliationTransaction {
    private final int memberId;
    private final double feeRate;

    public ChangeUnionAffiliationTransaction(final int empId, final int memberId, final double feeRate) {
        super(empId);
        this.memberId = memberId;
        this.feeRate = feeRate;
    }

    @Override
    protected Affiliation getAffiliation() {
        return new UnionAffiliation(feeRate);
    }

    @Override
    protected void updateMembership(final Employee employee) {
        GpayrollDatabase.addUnionMember(memberId, employee);
    }
}
