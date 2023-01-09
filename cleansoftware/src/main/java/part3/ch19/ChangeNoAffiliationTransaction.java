package part3.ch19;

import static part3.ch19.Application.GpayrollDatabase;

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
