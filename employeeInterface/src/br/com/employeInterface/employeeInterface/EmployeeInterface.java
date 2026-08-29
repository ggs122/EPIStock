package br.com.employeInterface.employeeInterface;

import br.com.loginInterface.loginInterface.LoginInterface;

public interface EmployeeInterface {
    void createEmployee (String employeeFirstName, String employeeMiddleName, String employeeLastname, String employeeIdNumber, String employeeCpfNumber, int chooseJobe_title, double employeeSalary, String hireDate);
    void terminationDateEmployee(long employeeEnrollmentNumber, String terminationDateEmployee);
    boolean findEmployee(long employeeEnrollmentNumber);
    void deleteEmployee(long myEmployeeEnrollmentNumber, String user, String password, long employeeEnrollmentNumberForDetele);
    void setLoginService(LoginInterface loginService);
    void  calculateTenure(long employeeEnrollmentNumber);
    void showJobe_title();
    void printEmployeeList();
    void printTerminatioDateEmployee();
}
