package part3.ch19.affiliations;

import static part3.ch19.application.Application.GpayrollDatabase;

import part3.ch19.payroll_domain.Affiliation;
import part3.ch19.payroll_domain.Employee;
import part3.ch19.payroll_domain.Transaction;

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
