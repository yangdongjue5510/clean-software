package part3.ch19;

public class NoAffiliation implements Affiliation {
    @Override
    public double calculateDeductions(final Paycheck paycheck) {
        return 0;
    }
}
