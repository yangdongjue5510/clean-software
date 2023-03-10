package part3.ch19.payroll_databaseimpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import part3.ch19.payroll_database.PayrollDatabase;
import part3.ch19.payroll_domain.Employee;

public class PayrollDatabaseImpl implements PayrollDatabase {

    private final Map<Integer, Employee> employees;
    private final Map<Integer, Employee> unionMembers;

    public PayrollDatabaseImpl(final Map<Integer, Employee> employees, final Map<Integer, Employee> unionMembers) {
        this.employees = employees;
        this.unionMembers = unionMembers;
    }

    @Override
    public Employee getEmployee(final int empId) {
        return employees.get(empId);
    }

    @Override
    public void addEmployee(final int empId, final Employee employee) {
        employee.setId(empId);
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

    @Override
    public void addUnionMember(final int memberId, final Employee employee) {
        unionMembers.put(memberId, employee);
    }

    @Override
    public Employee getUnionMember(final int memberId) {
        return unionMembers.get(memberId);
    }

    @Override
    public void deleteMember(final int memberId) {
        unionMembers.remove(memberId);
    }

    @Override
    public List<Employee> getEmployees() {
       return new ArrayList<>(employees.values());
    }
}
