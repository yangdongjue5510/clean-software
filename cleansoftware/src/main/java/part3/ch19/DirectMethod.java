package part3.ch19;

public class DirectMethod implements PaymentMethod {
    private final String bank;
    private final String account;

    public DirectMethod(final String bank, final String account) {
        this.bank = bank;
        this.account = account;
    }
}
