package br.com.employee.employee;

import br.com.employeInterface.employeeInterface.EmployeeInterface;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class EmployeeImpl implements EmployeeInterface {

    public enum Jobe_Title {
        LANTERNAGEM, PINTURA, ESTOFADOR, ELETRICISTA, MECANICO, TEC_REFRI, MANOBRISTA, JATISTA, BORRACHEIRO, ABASTECEDOR, INEXISTENTE
    }

    public enum Status {
        Ativo, Inativo, INEXISTENTE
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
    private LocalDate hireDate = LocalDate.of(0001, 01, 01);
    private LocalDate dateToday = LocalDate.now();
    private String dateTodayString = dateToday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private LocalDate terminationDate = LocalDate.of(0001, 01, 01);
    private Status is_Active;

    private Locale localeBr = Locale.forLanguageTag("pt-BR");
    private final String currency = "BRL";
    private NumberFormat numberFormat;
    private String newSalaryFormated;

    private EmployeeImpl(long employeeId, long employeeEnrollmentNumber, String employeeFirstName, String employeeMiddleName, String employeeLastname, String employeeIdNumber, String employeeCpfNumber, Jobe_Title jobe_title, double employeeSalary, LocalDate hireDate, LocalDate terminationDate, Status is_Active) {
        this.employeeId = employeeId;
        this.employeeEnrollmentNumber = employeeEnrollmentNumber;
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastname = employeeLastname;
        this.employeeIdNumber = employeeIdNumber;
        this.employeeCpfNumber = employeeCpfNumber;
        this.jobe_title = jobe_title;
        this.employeeSalary = employeeSalary;
        this.hireDate = hireDate;
        this.terminationDate = terminationDate;
        this.is_Active = is_Active;
        this.numberFormat = NumberFormat.getCurrencyInstance();
        this.numberFormat.setCurrency(Currency.getInstance(currency));
        this.newSalaryFormated = numberFormat.format(employeeSalary);
    }

    public EmployeeImpl() {}

    static List<EmployeeImpl> employeeList = new ArrayList<>();

    public static List<EmployeeImpl> getEmployeeList() {
        return employeeList;
    }

    @Override
    public void createEmployee (String employeeFirstName, String employeeMiddleName, String employeeLastname, String employeeIdNumber, String employeeCpfNumber, int chooseJobe_title, double employeeSalary) {

      boolean isSimilarFullName = employeeList
                .stream()
                .anyMatch(e -> e.employeeFirstName.equalsIgnoreCase(employeeFirstName) && e.employeeMiddleName.equalsIgnoreCase(employeeMiddleName) && e.employeeLastname.equalsIgnoreCase(employeeLastname));

      boolean isSimilarIdAndCpfNumber = employeeList
              .stream()
              .anyMatch(e -> e.employeeIdNumber.equalsIgnoreCase(employeeIdNumber) && e.employeeCpfNumber.equalsIgnoreCase(employeeCpfNumber));

      boolean isSimilarEmployeeIdNumber = employeeList
              .stream()
              .anyMatch(e -> e.employeeIdNumber.equalsIgnoreCase(employeeIdNumber));

     boolean isSimilarEmployeerCpfNumber = employeeList
              .stream()
              .anyMatch(e -> e.employeeCpfNumber.equalsIgnoreCase(employeeCpfNumber));

        if (isSimilarFullName) {
            IO.println("-----------------------------------------------------------------------------------");
            IO.println(String.format(localeBr, "Funcionário: %s %s %s -> já cadastrado anteriormente!", employeeFirstName, employeeMiddleName, employeeLastname));
            IO.println("-----------------------------------------------------------------------------------");
        } else if (isSimilarEmployeeIdNumber){
            IO.println("-----------------------------------------------------------------------------------");
            IO.println(String.format(localeBr, "Funcionário: %s %s %s, Identidade Nº %s -> já cadastrado anteriormente!", employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber));
            IO.println("-----------------------------------------------------------------------------------");
        } else if (isSimilarEmployeerCpfNumber) {
            IO.println("-----------------------------------------------------------------------------------");
            IO.println(String.format(localeBr, "Funcionário: %s %s %s, CPF Nº %s -> já cadastrado anteriormente!", employeeFirstName, employeeMiddleName, employeeLastname, employeeCpfNumber));
            IO.println("-----------------------------------------------------------------------------------");
        } else if (isSimilarIdAndCpfNumber) {
            IO.println("-----------------------------------------------------------------------------------");
            IO.println(String.format(localeBr, "Funcionário: %s %s %s, Identidade Nº %s e CPF Nº %s -> já cadastrado anteriormente!", employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber, employeeCpfNumber));
            IO.println("-----------------------------------------------------------------------------------");
        } else if (isSimilarFullName && isSimilarIdAndCpfNumber) {
            IO.println("-----------------------------------------------------------------------------------");
            IO.println(String.format(localeBr, "Funcionário: %s %s %s, Identidade Nº %s e CPF Nº %s -> já cadastrado anteriormente!", employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber, employeeCpfNumber));
            IO.println("-----------------------------------------------------------------------------------");
        } else  if (isSimilarFullName && isSimilarEmployeeIdNumber) {
            IO.println("-----------------------------------------------------------------------------------");
            IO.println(String.format(localeBr, "Funcionário: %s %s %s, Identidade Nº %s -> já cadastrado anteriormente!", employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber));
            IO.println("-----------------------------------------------------------------------------------");
        } else if (isSimilarFullName && isSimilarEmployeerCpfNumber) {
            IO.println("-----------------------------------------------------------------------------------");
            IO.println(String.format(localeBr, "Funcionário: %s %s %s, CPF Nº %s -> já cadastrado anteriormente!", employeeFirstName, employeeMiddleName, employeeLastname, employeeCpfNumber));
            IO.println("-----------------------------------------------------------------------------------");
        } else {
            EmployeeImpl employee = new EmployeeImpl(employeeIdStatic++,employeeEnrollmentNumberStatic++, employeeFirstName, employeeMiddleName, employeeLastname, EmployeeUtils.returIdDefault(employeeIdNumber), EmployeeUtils.returnCpfDefault(employeeCpfNumber), EmployeeUtils.returnJoble_Title(chooseJobe_title), employeeSalary, dateToday, terminationDate, EmployeeUtils.returnStatus(1));
          employeeList.add(employee);
        }
    }

    @Override
    public void printEmployeeList() {
        if (!employeeList.isEmpty()) {
            IO.println("> Lista de Funcionários <");
            employeeList
                    .stream()
                    .forEach(e -> IO.println(e));
        }
    }

    @Override
    public String toString() {
        return String.format(localeBr, "Id: %d | Matrícula %d | Nome: %-15s %-15s %-15s | Identidade Nº %-12s | CPF: %-15s | Cargo: %-12s | Salário: %s | Admissão: %s | Demissão: %s | Status %s", employeeId, employeeEnrollmentNumber, employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber, employeeCpfNumber, jobe_title, newSalaryFormated, dateTodayString, terminationDate, is_Active);
    }
}
