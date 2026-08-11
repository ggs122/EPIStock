package br.com.employeInterface.employeeInterface;

public interface EmployeeInterface {
    void createEmployee (String employeeFirstName, String employeeMiddleName, String employeeLastname, String employeeIdNumber, String employeeCpfNumber, int chooseJobe_title, double employeeSalary, String hireDate);
    void terminationDateEmployee(long employeeEnrollmentNumber, String terminationDateEmployee);
    void findEmployee(int employeeEnrollmentNumber);
    void deleteEmployee(long employeeEnrollmentNumber);
    void  calculateTenure(long employeeEnrollmentNumber);
    void showJobe_title();
    void printEmployeeList();
    void printTerminatioDateEmployee();
}
