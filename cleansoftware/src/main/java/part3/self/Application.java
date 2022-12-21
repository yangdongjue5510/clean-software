package part3.self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import part3.self.domain.Employee;
import part3.self.view.AddEmployeeTransaction;

public class Application {
    public static void main(String[] args) throws IOException {
        final Map<Long, Employee> employees = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            final String line = bufferedReader.readLine();
            final AddEmployeeTransaction addEmployeeTransaction = new AddEmployeeTransaction(line);
            addEmployeeTransaction.execute(employees);
        }
    }
}
