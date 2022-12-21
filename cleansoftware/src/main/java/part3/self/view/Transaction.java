package part3.self.view;

import java.util.Map;
import part3.self.domain.Employee;

public interface Transaction {
    void execute(Map<Long, Employee> employee);
}
