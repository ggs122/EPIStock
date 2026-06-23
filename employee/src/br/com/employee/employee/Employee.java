package br.com.employee.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Employee {

    public enum Jobe_Title {
        LANTERNAGEM, PINTURA, ESTOFADOR, ELETRICISTA, MECANICO, TEC_REFRI, MANOBRISTA, JATISTA, BORRACHEIRO, ABASTECEDOR, INEXISTENTE
    }

    public enum Status {
        Ativo, Inativo
    }

    private long employeeId = employeeIdStatic;
    private static long employeeIdStatic = 1;
    private long employeeEnrollmentNumber = employeeEnrollmentNumberStatic;
    private static long employeeEnrollmentNumberStatic = 1000;
    private String employeeFirstName;
    private String employeeMiddleName;
    private String employeeLastname;
    private String employeeIdNumber;
    private String employeeCpfNumber;
    private Jobe_Title jobe_title;
    private double employeeSalary;
    private Status is_Active;

    private Employee(long employeeId, long employeeEnrollmentNumber, String employeeFirstName, String employeeMiddleName, String employeeLastname, String employeeIdNumber, String employeeCpfNumber, Jobe_Title jobe_title, double employeeSalary, Status is_Active) {
        this.employeeId = employeeId;
        this.employeeEnrollmentNumber = employeeEnrollmentNumber;
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastname = employeeLastname;
        this.employeeIdNumber = employeeIdNumber;
        this.employeeCpfNumber = employeeCpfNumber;
        this.jobe_title = jobe_title;
        this.employeeSalary = employeeSalary;
        this.is_Active = is_Active;
    }

    public Employee() {}

    static List<Employee> employeeList = new ArrayList<>();

    public static List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void createEmployee (String employeeFirstName, String employeeMiddleName, String employeeLastname, String employeeIdNumber, String employeeCpfNumber, Jobe_Title jobe_title, double employeeSalary, Status is_Active) {
        Employee employee = new Employee(employeeIdStatic++,employeeEnrollmentNumberStatic++, employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber, employeeCpfNumber, jobe_title, employeeSalary, is_Active);
        employeeList.add(employee);
    }

    public void printEmployeeList() {
        if (!employeeList.isEmpty()) {
            employeeList
                    .stream()
                    .forEach(e -> IO.println(e));
        }
    }

    @Override
    public String toString() {
        Locale localeBr = Locale.forLanguageTag("pt-BR");
        return String.format(localeBr, "Id: %d | Matrícula %d | Nome: %s %s %s | Identidade Nº %s | CPF: %s | Cargo: %s | Salário: %.2f | Status %s", employeeId, employeeEnrollmentNumber, employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber, employeeCpfNumber, jobe_title, employeeSalary, is_Active);
    }
}
