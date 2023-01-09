package part3.ch19;

import static part3.ch19.Application.GpayrollDatabase;

public class ChangeDirectMethodTransaction extends ChangeMethodTransaction {
    private final String bank;
    private final String account;

    public ChangeDirectMethodTransaction(final int empId, final String bank, final String account) {
        super(empId);
        this.bank = bank;
        this.account = account;
    }

    @Override
    public void execute() {
        final Employee employee = GpayrollDatabase.getEmployee(empId);
        employee.setMethod(new DirectMethod(bank, account));
    }
}
