package part3.ch19.payroll_database;

import java.util.List;
import part3.ch19.payroll_domain.Employee;

public interface PayrollDatabase {

    Employee getEmployee(int empId);
    void addEmployee(int empId, Employee employee);

    void deleteEmployee(int empId);
    void clear();

    void addUnionMember(int memberId, Employee employee);

    Employee getUnionMember(int memberId);

    void deleteMember(int memberId);

    List<Employee> getEmployees();
}
