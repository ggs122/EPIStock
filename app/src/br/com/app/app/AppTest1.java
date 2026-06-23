package br.com.app.app;


import br.com.employeInterface.employeeInterface.EmployeeInterface;

import java.util.ServiceLoader;

public class AppTest1 {

    static void main(String[] args) {

        var employee1 = ServiceLoader.load(EmployeeInterface.class).findFirst().orElseThrow();
        employee1.createEmployee("Soares", "Lima", "de Albuquerque", "123456-7", "089.365.987-70", 1, 3000, 1);
        employee1.createEmployee("Iracilda", "Lima", "Almeida", "1543", "055.056.718-65", 2, 2500, 1);
        employee1.printEmployeeList();

    }

}
