package br.com.employee.employee;

public class Employee {

    public enum Jobe_Title {
        LANTERNAGEM, PINTURA, ESTOFADOR, ELETRICISTA, MECANICO, TEC_REFRI, MANOBRISTA, JATISTA, BORRACHEIRO, ABASTECEDOR
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
    private boolean is_active;






}
