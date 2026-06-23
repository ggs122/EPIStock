import br.com.employee.employee.EmployeeImpl;
import br.com.employeInterface.employeeInterface.EmployeeInterface;

module employee {


    requires employeeInterface;

    provides EmployeeInterface with EmployeeImpl;
}