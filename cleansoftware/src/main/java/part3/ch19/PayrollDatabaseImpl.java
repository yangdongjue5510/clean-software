package part3.ch19;


import java.util.Map;

public class PayrollDatabaseImpl implements PayrollDatabase {

    private final Map<Integer, Employee> employees;

    public PayrollDatabaseImpl(final Map<Integer, Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Employee getEmployee(final int empId) {
        return employees.get(empId);
    }

    @Override
    public void addEmployee(final int empId, final Employee employee) {
        employees.put(empId, employee);
    }

    @Override
    public void deleteEmployee(final int empId) {
        employees.remove(empId);
    }

    @Override
    public void clear() {
        employees.clear();
    }
}
