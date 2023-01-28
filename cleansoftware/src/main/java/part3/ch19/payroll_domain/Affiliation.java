package part3.ch19.payroll_domain;

import part3.ch19.Paycheck;

public interface Affiliation {
    double calculateDeductions(Paycheck paycheck);
}
