package br.com.unifominventory.uniforminventory;

import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;
import br.com.loginInterface.loginInterface.LoginInterface;
import br.com.uniforminventoryinterface.uniforminventoryinterface.UniformInventoryInterface;

import java.util.ArrayList;
import java.util.List;

public class UniformInventoryImpl implements UniformInventoryInterface {

    long uniformId;

    long employeeEnrollmentNumber;

    long uniformAutoRepairAmountG;
    String uniformAutoRepairGCode;

    long uniformAutoRepairAmountGg;
    String uniformAutoRepairGgCode;

    long uniformAutoRepairM;
    String uniformAutoRepairMCode;

    long uniformAutoRepairP;
    String uniformAutoRepairPCode;

    long uniformAutoRepairPp;
    String uniformAutoRepairPpCode;



    long uniformShopForemanG;
    String uniformShopForemanGCode;

    long uniformShopForemanGg;
    String uniformShopForemanGgCode;

    long uniformShopForemanM;
    String uniformShopForemanMCode;

    long uniformShopForemanAmoutP;
    String uniformShopForemanPCode;

    long uniformShopForemanAmoutPp;
    String uniformShopForemanPpCode;

    EmployeeInterface EmployeeService;
    LoginInterface loginService;
    EpiStockInterface epiService;

    static List<UniformInventoryImpl> uniformInventoryList = new ArrayList<>();

}
