package br.com.employee.employee;

import br.com.employeInterface.employeeInterface.EmployeeInterface;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class EmployeeImpl implements EmployeeInterface {

    public enum Jobe_Title {
        LANTERNAGEM, PINTURA, ESTOFADOR, ELETRICISTA, MECANICO, TEC_REFRI, MANOBRISTA, JATISTA, BORRACHEIRO, ABASTECEDOR, INEXISTENTE, GERENTE, GERENTE_R
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

   public static List<EmployeeImpl> employeeList = new ArrayList<>();

    EmployeeImpl.Jobe_Title [][] jobeTitle = new EmployeeImpl.Jobe_Title[4][3];

    public static List<EmployeeImpl> getEmployeeList() {
        return employeeList;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public long getEmployeeEnrollmentNumber() {
        return employeeEnrollmentNumber;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public String getEmployeeLastname() {
        return employeeLastname;
    }

    public String getEmployeeIdNumber() {
        return employeeIdNumber;
    }

    public String getEmployeeCpfNumber() {
        return employeeCpfNumber;
    }

    public Jobe_Title getJobe_title() {
        return jobe_title;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public Status getIs_Active() {
        return is_Active;
    }

    @Override
    public void createEmployee (String employeeFirstName, String employeeMiddleName, String employeeLastname, String employeeIdNumber, String employeeCpfNumber, int chooseJobe_title, double employeeSalary, String hireDate) {

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
            try {
                LocalDate localHireDate = LocalDate.parse(hireDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                EmployeeImpl employee = new EmployeeImpl(employeeIdStatic++,employeeEnrollmentNumberStatic++, employeeFirstName, employeeMiddleName, employeeLastname, EmployeeUtils.returIdDefault(employeeIdNumber), EmployeeUtils.returnCpfDefault(employeeCpfNumber), EmployeeUtils.returnJoble_Title(chooseJobe_title), employeeSalary, localHireDate, terminationDate, EmployeeUtils.returnStatus(1));
                employeeList.add(employee);
            } catch (DateTimeParseException d) {
                IO.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                IO.println(String.format(localeBr, "Data formato inválido! Impossível cadastrar o funcionário: %s %s %s, Identidade Nº %s, CPF Nº %s", employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber, employeeCpfNumber));
                IO.println("Digite uma data válida.");
                IO.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    //TODO conferir e add interface.
    public void showJobe_title() {
        jobeTitle[0][0] = Jobe_Title.LANTERNAGEM;     jobeTitle[0][1] = Jobe_Title.PINTURA;     jobeTitle[0][2] = Jobe_Title.ESTOFADOR;
        jobeTitle[1][0] = Jobe_Title.ELETRICISTA;     jobeTitle[1][1] = Jobe_Title.MECANICO;    jobeTitle[1][2] = Jobe_Title.TEC_REFRI;
        jobeTitle[2][0] = Jobe_Title.MANOBRISTA;      jobeTitle[2][1] = Jobe_Title.JATISTA;     jobeTitle[2][2] = Jobe_Title.BORRACHEIRO;
        jobeTitle[3][0] = Jobe_Title.ABASTECEDOR;     jobeTitle[3][1] = Jobe_Title.GERENTE;     jobeTitle[3][2] = Jobe_Title.GERENTE_R;
        showJobeTitle();
    }

    //TODO conferir e add interface.
    private void showJobeTitle() {
        for (int i = 0; i < jobeTitle.length; i++) {
            for (int j = 0; j < i; j++) {
               EmployeeImpl.Jobe_Title jT = jobeTitle[i][j];
            }
        }
    }

    @Override
    public void findEmployee(int employeeEnrollmentNumber) {

      boolean isEmployee = employeeList
                .stream()
                .anyMatch(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber);

      if (isEmployee) {
          if (!employeeList.isEmpty()) {

              IO.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
              IO.println("Funcionário encontrado:");
              employeeList
                      .stream()
                      .filter(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber)
                      .forEach(e -> IO.println(e));
              IO.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
          } else {
              IO.println("--------------------------------------------------");
              IO.println("Não há clientes cadastrados no sistema -> Cadastre");
              IO.println("--------------------------------------------------");
          }
      } else {
          IO.println("-----------------------------------------------------------");
          IO.println(String.format(localeBr, "Matrícula %d -> Inexistente!!", employeeEnrollmentNumber));
          IO.println("-----------------------------------------------------------");
      }
    }

    @Override
    public void deleteEmployee(int employeeEnrollmentNumber) {
        if (!employeeList.isEmpty()) {
            IO.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            employeeList
                    .stream()
                    .filter(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber)
                    .forEach(e -> IO.println(e));

            boolean isEmployee = employeeList
                    .removeIf(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber);

            if (isEmployee) {
                IO.println("Funcionário deletado com sucesso!");
                IO.println("---------------------------------");
                IO.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            }
        } else {
            IO.println("--------------------------------------------------");
            IO.println("Não há clientes cadastrados no sistema -> Cadastre");
            IO.println("--------------------------------------------------");
        }
    }

    @Override
    public void terminationDateEmployee(long employeeEnrollmentNumber, String terminationDateEmployee) {

        try {
            LocalDate terminationDateEmployeeLocalDate = LocalDate.parse(terminationDateEmployee, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            boolean foundEmployee = employeeList
                    .stream()
                    .anyMatch(e -> e.employeeEnrollmentNumber == employeeEnrollmentNumber);

            boolean terminationEmployeeLocalDateIsBefore = employeeList
                    .stream()
                    .anyMatch(e -> e.employeeEnrollmentNumber == employeeEnrollmentNumber && e.hireDate.isBefore(terminationDateEmployeeLocalDate));


            if (foundEmployee) {

                if (terminationEmployeeLocalDateIsBefore) {

                    employeeList
                            .stream()
                            .filter(e -> e.employeeEnrollmentNumber == employeeEnrollmentNumber)
                            .forEach(e -> {
                                e.is_Active = Status.Inativo;
                                e.terminationDate = terminationDateEmployeeLocalDate;
                            });
                    IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    employeeList
                            .stream()
                            .filter(e -> e.employeeEnrollmentNumber == employeeEnrollmentNumber)
                            .forEach(e -> IO.println(String.format(localeBr, "Funcionário: %s %s %s | Matrícula Nº : %d -> demitido com sucesso", e.employeeFirstName, e.employeeMiddleName, e.employeeLastname, e.employeeEnrollmentNumber)));
                    IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                } else {
                    IO.println("---------------------------------------------------------------------------------------------");
                    IO.println(String.format(localeBr, "Data de demissão: %s, Não pode ser anterior a data de admissão!\nPor isso não foi processado a demissão do funcionário.", terminationDateEmployeeLocalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                    IO.println("---------------------------------------------------------------------------------------------");
                }

            } else {
                IO.println("------------------------------------------------------------------------------");
                IO.println(String.format(localeBr, "Funcionário Matrícula %d -> Não encontrada ou inexistente", employeeEnrollmentNumber));
                IO.println("------------------------------------------------------------------------------");
            }
        } catch (DateTimeParseException d) {
            IO.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
            IO.println(String.format(localeBr, "Data formato inválido! Impossível cadastrar o funcionário: %s %s %s, Identidade Nº %s, CPF Nº %s", employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber, employeeCpfNumber));
            IO.println("Digite uma data válida.");
            IO.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public void printEmployeeList() {
        if (!employeeList.isEmpty()) {
            IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            IO.println("> Lista de Funcionários <");
            employeeList
                    .stream()
                    .forEach(e -> IO.println(e));
            IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public String toString() {

            return String.format(localeBr, "Id: %d | Matrícula %d | Nome: %-15s %-15s %-15s | Identidade Nº %-12s | CPF: %-15s | Cargo: %-12s | Salário: %-15s | Admissão: %s | Demissão: %s | Status %s", employeeId, employeeEnrollmentNumber, employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber, employeeCpfNumber, jobe_title, newSalaryFormated, hireDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), terminationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), is_Active);
    }
}
