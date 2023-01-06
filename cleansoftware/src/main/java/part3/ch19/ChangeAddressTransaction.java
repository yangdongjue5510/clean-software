package part3.ch19;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    private final String address;

    public ChangeAddressTransaction(final int empId, final String address) {
        super(empId);
        this.address = address;
    }

    @Override
    public void execute() {
        final Employee employee = Application.GpayrollDatabase.getEmployee(empId);
        employee.setAddress(address);
    }
}
