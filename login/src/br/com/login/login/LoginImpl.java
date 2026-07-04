package br.com.login.login;

import br.com.employee.employee.EmployeeImpl;
import br.com.loginInterface.loginInterface.LoginInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LoginImpl implements LoginInterface {

    enum AuthenticationType {
        LOGIN, LOGIN_MASTER
    }

    private String user;
    private String password;
    private long employeeEnrollmentNumber;
    private String passwordReminder;
    private AuthenticationType authenticationType;
    private Locale localeBr = Locale.forLanguageTag("pt-BR");

    //TODO falta atribuição de AuthenticationType no construtor
    private LoginImpl(long employeeEnrollmentNumber, String user, String password, String passwordReminder) {
        this.employeeEnrollmentNumber = employeeEnrollmentNumber;
        this.user = user;
        this.password = password;
        this.passwordReminder = passwordReminder;
    }

    public LoginImpl() {}

    static List<LoginImpl> loginList = new ArrayList<>();
    static List<EmployeeImpl> employeeList = EmployeeImpl.getEmployeeList();

    public long getEmployeeEnrollmentNumber() {
        return employeeEnrollmentNumber;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    //TODO falta o método get de aunthenticationType

    public String getPasswordReminder() {
        return passwordReminder;
    }

    @Override
    public void createLogin(long employeeEnrollmentNumber, String user, String password, String passwordReminder) {

      boolean isEmployee = employeeList
                .stream()
                        .anyMatch(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber);

      boolean isUser = user.matches("[A-Z]{1}([a-z])+");
      boolean isPassword = password.matches("[A-Za-z]{1}\\.\\d{6}");

      if (isEmployee && isUser && isPassword) {
          LoginImpl login = new LoginImpl(employeeEnrollmentNumber, user,password, passwordReminder);
          loginList.add(login);
      }

    }

    private boolean checkingIsActive(String user, String password) {
    long employeeEnrollmentNumberLong = loginList
                .stream()
                .filter(l -> l.user.equals(user) && l.password.equals(password))
                .mapToLong(l -> l.employeeEnrollmentNumber)
                .sum();

  var isStatusList = employeeList
            .stream()
            .filter(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumberLong)
            .map(e -> e.getIs_Active())
            .toList();


         boolean isActive = isStatusList
                  .stream()
                  .anyMatch(i -> i == EmployeeImpl.Status.Ativo);

        return isActive;
    }

    @Override
    public void showLogins(String user, String password) {
        if (!loginList.isEmpty()) {
            boolean isLogin = loginList
                    .stream()
                    .anyMatch(l -> l.user.equals(user) && l.password.equals(password));

            if (isLogin) {
                if (checkingIsActive(user, password)) {
                    IO.println("-------------------------------------------------------------------------------------");
                    IO.println("> Dados do Login <");
                    loginList
                            .stream()
                            .filter(l -> l.user.equals(user) && l.password.equals(password))
                            .forEach(l -> IO.println(l));
                    IO.println("-------------------------------------------------------------------------------------");
                } else {
                    IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    IO.println(String.format(localeBr, "O usuário %s, foi demitido da empresa -> Impossível mostrar a senha. Para mostrar essa senha você precisa ser LoginMaster", user));
                    IO.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            } else {
                IO.println("-----------------------------");
                IO.println("Usuário e senha não conferem.");
                IO.println("-----------------------------");
            }
        } else {
            IO.println("------------------------");
            IO.println("Nenhum login cadastrado.");
            IO.println("------------------------");
        }
    }

    //TODO Falta aunthenticationType no método toString

    @Override
    public String toString() {
        return String.format(localeBr, "Matrícula: %d | Usuário: %s | Senha: %s | Lembrete de Senha: %s", employeeEnrollmentNumber, user, password, passwordReminder);
    }
}
