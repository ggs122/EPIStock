package br.com.employee.employee;

import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.serializationsutils.serializationutils.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class EmployeeImpl implements EmployeeInterface, Serializable {

    public enum Jobe_Title {
        LANTERNAGEM(1), PINTURA(2), ESTOFADOR(3), ELETRICISTA(4), MECANICO(5), TEC_REFRI(6), MANOBRISTA(7), JATISTA(8), BORRACHEIRO(9), ABASTECEDOR(10), INEXISTENTE(11), GERENTE(12), GERENTE_R(13), INATIVO(14);

        public int jobeTitleNumber;

        Jobe_Title(int jobeTitleNumber) {
            this.jobeTitleNumber = jobeTitleNumber;
        }

        public int getJobeTitleNumber() {
            return jobeTitleNumber;
        }
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
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeImpl.class);

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

    EmployeeImpl.Jobe_Title [][] jobeTitle = new EmployeeImpl.Jobe_Title[5][3];

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
                IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                IO.println("DADOS DA ADMISSÃO:");
                employeeList
                        .stream()
                                .filter(e -> e.employeeIdNumber == employeeIdNumber && e.employeeCpfNumber == employeeCpfNumber)
                                        .forEach(e -> IO.println(e));
                LOGGER.info("ADMITIDO COM SUCESSO!!");
                IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            } catch (DateTimeParseException d) {
                IO.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                IO.println(String.format(localeBr, "Data formato inválido! Impossível cadastrar o funcionário: %s %s %s, Identidade Nº %s, CPF Nº %s", employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber, employeeCpfNumber));
                IO.println("Digite uma data válida.");
                IO.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    @Override
    public void showJobe_title() {

        for (int i = 0; i < jobeTitle.length; i++) {
            for (int j = 0; j < jobeTitle[i].length; j++) {
                jobeTitle[i][j] = Jobe_Title.INATIVO;
            }
        }
        Jobe_Title[] value = Jobe_Title.values();
        int cont = 0;

        for (int i = 0; i < jobeTitle.length; i++) {
            for (int j = 0; j < jobeTitle[i].length; j++) {
                if (cont < value.length) {
                    jobeTitle[i][j] = value[cont];
                    cont++;
                }
            }
        }
        showJobeTitle();
    }

    private void showJobeTitle() {
        IO.println("--------------------------------------------------------------------------------");
        IO.println("> Opções de profissões da empresa <");
        IO.println();
        if (jobeTitle.length > 0) {
            for (int i = 0; i < jobeTitle.length; i++) {
                for (int j = 0; j < jobeTitle[i].length; j++) {
                    System.out.print(String.format(localeBr, "Nº %-5d  %-15s", jobeTitle[i][j].getJobeTitleNumber(), jobeTitle[i][j]));
                }
                System.out.println();
            }
        }
        System.out.println();
        LOGGER.info("Opções de profissões mostrada com sucesso!");
        IO.println("--------------------------------------------------------------------------------");
    }

    @Override
    public void findEmployee(int employeeEnrollmentNumber) {

      boolean isEmployee = employeeList
                .stream()
                .anyMatch(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber);

      if (isEmployee) {
          if (!employeeList.isEmpty()) {

              IO.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
              LOGGER.info(String.format(localeBr, "Funcionário da Matrícula: %d -> ENCONTRADO COM SUCESSO!", employeeEnrollmentNumber));
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
    public void deleteEmployee(long employeeEnrollmentNumber) {
        if (!employeeList.isEmpty()) {
            IO.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            employeeList
                    .stream()
                    .filter(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber)
                    .forEach(e -> IO.println(String.format(localeBr, "Id: %d | Matrícula: %d | Nome: %s %s %s | Função: %s", e.employeeId, e.employeeEnrollmentNumber, e.employeeFirstName, e.employeeMiddleName, e.employeeLastname, e.getJobe_title())));

            boolean isEmployee = employeeList
                    .removeIf(
                            e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber

                    );

            if (isEmployee) {
                //TODO -> verificar depois
                LOGGER.info(String.format(localeBr, "Funcionário Mat: %d -> DELETADO COM SUCESSO!", employeeEnrollmentNumber));
                IO.println("---------------------------------");
                IO.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            } else {
                IO.println(String.format(localeBr, "Funcionário da Mat:%s -> INEXISTENTE", employeeEnrollmentNumber));
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
                    IO.println("DADOS DA DEMISSÃO");
                    employeeList
                            .stream()
                            .filter(e -> e.employeeEnrollmentNumber == employeeEnrollmentNumber)
                            .forEach(e -> IO.println(String.format(localeBr, "Funcionário: %s %s %s | Matrícula Nº : %d", e.employeeFirstName, e.employeeMiddleName, e.employeeLastname, e.employeeEnrollmentNumber)));
                    LOGGER.info("DEMITIDO COM SUCESSO:.");
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
        Path file1 = Path.of("file1.bin");
        if (!employeeList.isEmpty()) {
            IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            IO.println("> Lista de Funcionários <");
            employeeList
                    .stream()
                    .forEach(
                            e -> {
                                        IO.println(e);

                                                try {
                                                    SerializationUtils.toFile(e, file1);
                                                } catch (Exception e1) {
                                                    throw new RuntimeException(e1);
                                                }
                            });
            EmployeeImpl.loginEmployee();
            IO.println("------------------------------------------------------------");
            LOGGER.info("Lista de empregados mostrada com sucesso!");
            IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public void  calculateTenure(long employeeEnrollmentNumber) {
      boolean isEmployee = employeeList
                .stream()
                .anyMatch(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber && e.is_Active.equals(Status.Inativo));

      if (isEmployee) {
          employeeList
                  .stream()
                  .filter(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber)
                  .forEach(e -> {
                      IO.println("-----------------------------------------------------------");
                      IO.println("PERÍODO TRABALHADO:");
                      IO.println();

                   IO.println("Dados do Funcionário:");
                   IO.println();
                   IO.println(String.format(localeBr, "Mat: %s | Nome: %s %s %s", e.getEmployeeEnrollmentNumber(), e.getEmployeeFirstName(), e.getEmployeeMiddleName(), e.getEmployeeLastname()));
                   IO.println();
                      System.out.println(String.format(localeBr, "Data de Demissão -> %s ", e.terminationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", localeBr))));
                      System.out.println(String.format(localeBr, "Data de Admissão -> %s ", e.hireDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", localeBr))));
                      IO.println();

                     Period period = Period.between(e.hireDate, e.terminationDate);
                   int localIntDay = period.getDays();
                   int localIntMonth = period.getMonths();
                   int localIntYear = period.getYears();

                   StringBuilder sb = new StringBuilder();
                   if (localIntYear > 0) sb.append(localIntYear).append(localIntYear == 1 ? " ano: " : " anos ");
                   if (localIntMonth > 0) {
                       if (sb.length() > 0) sb.append(", ");
                       sb.append(localIntMonth).append(localIntMonth == 1 ? " mês " : "meses");
                   }
                   if (localIntDay > 0) {
                       if (sb.length() > 0) sb.append(", ");
                       sb.append(localIntDay).append(localIntDay == 1 ? " dia " : " dias ");
                   }
                   if (sb.length() == 0) {
                       sb.append("Menos de 1 dia");
                   }

                      LOGGER.info("PERÍODO TRABALHADO NA EMPRESA");
                   String p = sb.toString();
                      IO.println("                         " + p);

                          IO.println("-----------------------------------------------------------");
                  });
      } else {
          IO.println(String.format(localeBr, "Matrícula %d -> Inexistente \nou\no funcionário ainda não foi demitido.", employeeEnrollmentNumber));
          IO.println("-----------------------------------------------------------");
      }
    }

    private static void loginEmployee() {
        File employeLoginPathToFile = new File("EmployeeLogin");
        if (!employeLoginPathToFile.exists()) {
          employeLoginPathToFile.mkdir();
      }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("EmployeeLogin/login.txt", StandardCharsets.UTF_8))) {
            bw.write("> Lista de Funcionários <");
            bw.newLine();
            bw.newLine();
            employeeList
                    .stream()
                    .forEach(e -> {
                        try {
                            bw.write(e.toString().concat("\n"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {

            return String.format(localeBr, "Id: %d | Matrícula %d | Nome: %-15s %-15s %-15s | Identidade Nº %-12s | CPF: %-15s | Cargo: %-12s | Salário: %-15s | Admissão: %s | Demissão: %s | Status %s", employeeId, employeeEnrollmentNumber, employeeFirstName, employeeMiddleName, employeeLastname, employeeIdNumber, employeeCpfNumber, jobe_title, newSalaryFormated, hireDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), terminationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), is_Active);
    }
}
