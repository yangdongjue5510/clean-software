package part3.ch19;

public interface PayrollDatabase {

    Employee getEmployee(int empId);
    void addEmployee(int empId, Employee employee);

    void deleteEmployee(int empId);
    void clear();
}
