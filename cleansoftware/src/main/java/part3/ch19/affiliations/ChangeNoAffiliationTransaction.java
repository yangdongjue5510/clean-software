package part3.ch19.affiliations;

import static part3.ch19.application.Application.GpayrollDatabase;

import part3.ch19.payroll_domain.Affiliation;
import part3.ch19.payroll_domain.Employee;

public class ChangeNoAffiliationTransaction extends ChangeAffiliationTransaction {
    private final int memberId;

    public ChangeNoAffiliationTransaction(final int empId, final int memberId) {
        super(empId);
        this.memberId = memberId;
    }

    @Override
    protected Affiliation getAffiliation() {
        return new NoAffiliation();
    }

    @Override
    protected void updateMembership(final Employee employee) {
        if (employee.getAffiliation() instanceof UnionAffiliation) {
            GpayrollDatabase.deleteMember(memberId);
        }
    }
}
