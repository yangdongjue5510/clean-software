package part3.ch19;

import java.util.List;

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
