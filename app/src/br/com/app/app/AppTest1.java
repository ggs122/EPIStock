package br.com.app.app;


import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;
import br.com.loginInterface.loginInterface.LoginInterface;

import java.util.ServiceLoader;

public class AppTest1 {

    static void main(String[] args) {

        var employee1 = ServiceLoader.load(EmployeeInterface.class).findFirst().orElseThrow();
        employee1.createEmployee("Soares", "Lima", "de Albuquerque", "30.149.155-3", "089.365.987-70", 1, 3000, "15/05/1990");
        employee1.createEmployee("Iracilda", "Lima", "Almeida", "20.169.255-5", "055.056.718-65", 2, 2500, "17/07/2020");
        employee1.createEmployee("Fernanda", "Souza", "Almeida", "20.169.255-6", "055.056.718-67", 3, 2500, "23/03/2026");
        employee1.createEmployee("Ivanildo", "Lima", "de Souza","30.135.356-9", "060.055.817-99",13, 7500, "15/08/1999");
        employee1.createEmployee("Rogério", "Lopes", "Martins", "22.567.333-8", "135.065.255-90", 12, 12500, "15/08/1989");
        employee1.terminationDateEmployee(1000, "15/09/1996");
        employee1.printEmployeeList();

       LoginInterface login1 = ServiceLoader.load(LoginInterface.class).findFirst().orElseThrow();
       login1.createLogin(1000, "Soares", "S.123456", "Niver", 1);
       login1.createLogin(1001, "Iracilda", "I.654321", "Ir", 1);
       login1.createLogin(1003, "Ivanildo", "I.131326", "Pt", 2);
       login1.createLogin(1003, "Ivanildo", "I.131326", "Pt", 2);
       login1.showLogins("Soares", "S.123456");
       login1.showLogins("Iracilda", "I.654321");
       login1.showLogins("Ivanildo", "I.131326");
       login1.changeLogin("Ivanildo", "I.131326", 1001, "Iracilda", "I.654321", "Ira", "I.263263");
       login1.showLogins("Ira", "I.263263");

       EpiStockInterface stock1 = ServiceLoader.load(EpiStockInterface.class).findFirst().orElseThrow();

       login1.showAllLogins("Ivanildo", "I.131326");

       login1.showSpecificLogin(1001, "Ivanildo", "I.131326");

        login1.deleteLogin(1000, "Ivanildo", "I.131326");

        login1.showAllLogins("Ivanildo", "I.131326");

        employee1.findEmployee(1000);
//        employee1.deleteEmployee(1000);
        employee1.printEmployeeList();

        login1.createLogin(1004, "Rog", "R.366589", "Rog1", 2);
        login1.showAllLogins("Rog", "R.366589");

        login1.rememberLogin("Rog1");

        employee1.showJobe_title();

        stock1.addPpeToStock("10.302.05", "Protetor Auricular", "Plug", "123456", "Atenuação - ruídos - até 25db", 45);
        stock1.addPpeToStock("20.305.60", "Óculos de proteção", "Transparente", "356598", "Proteção - partículas voláteis", 75);
        stock1.addPpeToStock("55.363.25", "Respirador facial", "Descartável", "558987", "Pinturas - automotívas", 120);
        stock1.addPpeToStock("55.363.25", "Respirador facial", "Descartável", "558987", "Pinturas - automotívas", 120);
        stock1.printEpiStock();
        stock1.personalProtectiveEquipmentDelivery("10.302.05", 1, 1001, "20/05/2026 20:30");
        stock1.personalProtectiveEquipmentDelivery("55.363.25", 1, 1001, "05/07/2026 14:30");
        stock1.personalProtectiveEquipmentDelivery("55.363.25", 1, 1002, "15/08/2026 22:50");
        stock1.personalProtectiveEquipmentDelivery("10.302.05", 1, 1000, "10/03/2027 15:30");
//        stock1.deleteSpecificEpiForCodRef("10.302.05");
        //stock1.deleteAllEpiStock();
        stock1.printEpiStock();
        stock1.printEpiStockUseds();
        stock1.printSpecificEpiUsed(1001);

        employee1.calculateTenure(1000);

    }
}
