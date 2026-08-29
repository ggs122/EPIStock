import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.loginInterface.loginInterface.LoginInterface;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;



module app {

    requires employeeInterface;
    requires loginInterface;
    requires epiStockInterface;
    requires org.slf4j;



//    requires uniforminventoryinterface;





    opens br.com.app.app to ch.qos.logback.core;


    uses EmployeeInterface;
    uses LoginInterface;
    uses EpiStockInterface;




}