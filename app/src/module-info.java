import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.loginInterface.loginInterface.LoginInterface;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;
import br.com.uniforminventoryinterface.uniforminventoryinterface.UniformInventoryInterface;


module app {

    requires employeeInterface;
    requires loginInterface;
    requires epiStockInterface;

    requires org.slf4j;
    requires uniforminventoryinterface;
    requires uniformInventoryInterface;

    opens br.com.app.app to ch.qos.logback.core;


    uses EmployeeInterface;
    uses LoginInterface;
    uses EpiStockInterface;
    uses UniformInventoryInterface;



}