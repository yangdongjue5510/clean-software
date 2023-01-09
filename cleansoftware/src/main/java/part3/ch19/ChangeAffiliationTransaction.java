package part3.ch19;

import static part3.ch19.Application.GpayrollDatabase;

public abstract class ChangeAffiliationTransaction implements Transaction {

    protected int empId;

    protected ChangeAffiliationTransaction(final int empId) {
        this.empId = empId;
    }

    protected abstract Affiliation getAffiliation();
    protected abstract void updateMembership(Employee employee);

    public void execute() {
        final Employee employee = GpayrollDatabase.getEmployee(empId);
        employee.setAffiliation(getAffiliation());
        updateMembership(employee);
    }
}
