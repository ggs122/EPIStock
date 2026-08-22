import br.com.employee.employee.EmployeeImpl;
import br.com.employeInterface.employeeInterface.EmployeeInterface;

module employee {


    requires employeeInterface;
    requires org.slf4j;
    requires serializationsutils;
    requires transitive loginInterface;

    exports br.com.employee.employee;

    provides EmployeeInterface with EmployeeImpl;
}