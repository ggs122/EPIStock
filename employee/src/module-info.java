import br.com.employee.employee.EmployeeImpl;
import br.com.employeInterface.employeeInterface.EmployeeInterface;

module employee {
    exports br.com.employee.employee;

    requires employeeInterface;

    provides EmployeeInterface with EmployeeImpl;
}