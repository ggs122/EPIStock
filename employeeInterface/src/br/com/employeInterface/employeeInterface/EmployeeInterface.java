package br.com.employeInterface.employeeInterface;

import java.time.LocalDate;

public interface EmployeeInterface {
    void createEmployee (String employeeFirstName, String employeeMiddleName, String employeeLastname, String employeeIdNumber, String employeeCpfNumber, int chooseJobe_title, double employeeSalary, String hireDate);
    void terminationDateEmployee(long employeeEnrollmentNumber, String terminationDateEmployee);
    void findEmployee(int employeeEnrollmentNumber);
    void deleteEmployee(int employeeEnrollmentNumber);
    void printEmployeeList();
}
