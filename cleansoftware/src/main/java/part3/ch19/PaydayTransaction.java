package part3.ch19;

import static part3.ch19.application.Application.GpayrollDatabase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import part3.ch19.payroll_domain.Employee;
import part3.ch19.payroll_domain.Transaction;

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
            final Paycheck paycheck = new Paycheck(employee.getPayPeriodStartDate(payDate), payDate);
            paychecks.put(employee.getId(), paycheck);
            employee.payday(paycheck);
        }
    }

    public Paycheck getPaycheck(final int empId) {
        return paychecks.get(empId);
    }
}
