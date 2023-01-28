package part3.ch19.application;

import java.util.HashMap;
import part3.ch19.payroll_database.PayrollDatabase;
import part3.ch19.payroll_databaseimpl.PayrollDatabaseImpl;

public class Application {
    public static final PayrollDatabase GpayrollDatabase = new PayrollDatabaseImpl(new HashMap<>(), new HashMap<>());
}
