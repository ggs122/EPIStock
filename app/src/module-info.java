import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.loginInterface.loginInterface.LoginInterface;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;

module app {

    requires employeeInterface;
    requires loginInterface;
    requires epiStockInterface;


    uses EmployeeInterface;
    uses LoginInterface;
    uses EpiStockInterface;


}