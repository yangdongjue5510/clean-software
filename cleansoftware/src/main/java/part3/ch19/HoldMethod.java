package part3.ch19;

public class HoldMethod implements PaymentMethod {
    private final String address;

    public HoldMethod(final String address) {
        this.address = address;
    }
}
