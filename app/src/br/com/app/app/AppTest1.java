package br.com.app.app;


import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.employee.employee.EmployeeImpl;

public class AppTest1 {

    static void main(String[] args) {

        EmployeeInterface employee1 = new EmployeeImpl();
        employee1.createEmployee("Soares", "Lima", "de Albuquerque", "123456-7", "089.365.987-70", 1, 3000, 1);
        employee1.createEmployee("Iracilda", "Lima", "Almeida", "1543", "055.056.718-65", 2, 2500, 1);
        ((EmployeeImpl) employee1).printEmployeeList();

    }

}
