package part3.ch19.affiliations;

import part3.ch19.Paycheck;
import part3.ch19.payroll_domain.Affiliation;

public class NoAffiliation implements Affiliation {
    @Override
    public double calculateDeductions(final Paycheck paycheck) {
        return 0;
    }
}
