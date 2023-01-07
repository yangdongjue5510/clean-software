package part3.ch19;

import java.util.HashMap;

public class Application {
    public static final PayrollDatabase GpayrollDatabase = new PayrollDatabaseImpl(new HashMap<>(), new HashMap<>());
}
