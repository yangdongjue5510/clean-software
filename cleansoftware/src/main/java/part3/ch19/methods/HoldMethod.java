package part3.ch19.methods;

import part3.ch19.Paycheck;
import part3.ch19.payroll_domain.PaymentMethod;

public class HoldMethod implements PaymentMethod {
    private final String address;

    public HoldMethod(final String address) {
        this.address = address;
    }

    @Override
    public void pay(final Paycheck paycheck) {
        paycheck.setField("Disposition", "Hold");
    }
}
