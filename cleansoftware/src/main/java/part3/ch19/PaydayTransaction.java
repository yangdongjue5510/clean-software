package part3.ch19;

import static part3.ch19.Application.GpayrollDatabase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PaydayTransaction implements Transaction {
    private final LocalDate payDate;
    private final Map<Integer, Paycheck> paychecks = new HashMap<>();

    public PaydayTransaction(final LocalDate payDate) {
        this.payDate = payDate;
    }

    @Override
    public void execute() {
        GpayrollDatabase.getEmployees()
                .forEach(this::payday);
    }

    private void payday(final Employee employee) {
        if (employee.isPayDay(payDate)) {
            final Paycheck paycheck = new Paycheck(payDate);
            paychecks.put(employee.getId(), paycheck);
            employee.payday(paycheck);
        }
    }

    public Paycheck getPaycheck(final int empId) {
        return paychecks.get(empId);
    }
}
