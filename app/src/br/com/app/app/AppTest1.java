package br.com.app.app;


import br.com.employeInterface.employeeInterface.EmployeeInterface;

import java.time.LocalDate;
import java.util.ServiceLoader;

public class AppTest1 {

    static void main(String[] args) {

        var employee1 = ServiceLoader.load(EmployeeInterface.class).findFirst().orElseThrow();
        employee1.createEmployee("Soares", "Lima", "de Albuquerque", "30.149.155-3", "089.365.987-70", 1, 3000, "15/05/1990");
        employee1.createEmployee("Iracilda", "Lima", "Almeida", "20.169.255-5", "055.056.718-65", 2, 2500, "17/07/2020");
        employee1.createEmployee("Fernanda", "Souza", "Almeida", "20.169.255-6", "055.056.718-67", 2, 2500, "23/03/2026");
        employee1.terminationDateEmployee(1000, "15/05/1991");
        employee1.printEmployeeList();

    }

}
