
import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.login.login.LoginImpl;
import br.com.loginInterface.loginInterface.LoginInterface;

module login {

    requires employee;
    requires employeeInterface;
    requires loginInterface;

    exports br.com.login.login;

    provides br.com.loginInterface.loginInterface.LoginInterface with br.com.login.login.LoginImpl;




}