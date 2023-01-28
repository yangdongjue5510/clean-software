package part3.ch19.methods;

import part3.ch19.Paycheck;
import part3.ch19.payroll_domain.PaymentMethod;

public class DirectMethod implements PaymentMethod {
    private final String bank;
    private final String account;

    public DirectMethod(final String bank, final String account) {
        this.bank = bank;
        this.account = account;
    }

    @Override
    public void pay(final Paycheck paycheck) {
        System.out.printf("%s 은행 계좌 번호 %s로 %f $ 입금", bank, account, paycheck.getNetPay());
    }
}
