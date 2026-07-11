package br.com.login.login;

import br.com.employee.employee.EmployeeImpl;
import br.com.loginInterface.loginInterface.LoginInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LoginImpl implements LoginInterface {

    enum AuthenticationType {
        LOGIN, LOGIN_MASTER, INEXISTENTE
    }

    private String user;
    private String password;
    private long employeeEnrollmentNumber;
    private String passwordReminder;
    private AuthenticationType authenticationType;
    private Locale localeBr = Locale.forLanguageTag("pt-BR");


    private LoginImpl(long employeeEnrollmentNumber, String user, String password, String passwordReminder, AuthenticationType authenticationType) {
        this.employeeEnrollmentNumber = employeeEnrollmentNumber;
        this.user = user;
        this.password = password;
        this.passwordReminder = passwordReminder;
        this.authenticationType = authenticationType;
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

    public String getPasswordReminder() {
        return passwordReminder;
    }

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public static List<LoginImpl> getLoginList() {
        return loginList;
    }

    @Override
    public void createLogin(long employeeEnrollmentNumber, String user, String password, String passwordReminder, int authenticationType) {

      boolean isEmployee = employeeList
                .stream()
                        .anyMatch(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber);

      boolean isUser = user.matches("[A-Z]{1}([a-z])+");
      boolean isPassword = password.matches("[A-Za-z]{1}\\.\\d{6}");

    boolean isLogin = loginList
              .stream()
              .anyMatch(l -> l.user.equals(user) && l.password.equals(password) && l.passwordReminder.equals(passwordReminder) && l.authenticationType.equals(LoginUtils.returnAuthenticationType(authenticationType)));

      if (isEmployee) {
          if (isUser && isPassword) {
              if (isLogin) {
                  IO.println("---------------------------------------");
                  IO.println("Login com usuário e senha já existente.");
                  IO.println("---------------------------------------");
              } else {
                  LoginImpl login = new LoginImpl(employeeEnrollmentNumber, user,password, passwordReminder, LoginUtils.returnAuthenticationType(authenticationType));
                  loginList.add(login);
              }
          } else {
              IO.println("---------------------------------------------------------------------------------------------");
              IO.println(String.format(localeBr, "Usuário: %s e Password: %d -> Formato inválido", user, password));
              IO.println("---------------------------------------------------------------------------------------------");
          }
      } else {
          IO.println("------------------------------------------------------------------------------------------------");
          IO.println(String.format(localeBr, "Login com a matrícula %s -> inexistente", employeeEnrollmentNumber));
          IO.println("------------------------------------------------------------------------------------------------");
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
                    IO.println("-------------------------------------------------------------------------------------------------------");
                    IO.println("> Dados do Login <");
                    loginList
                            .stream()
                            .filter(l -> l.user.equals(user) && l.password.equals(password))
                            .forEach(l -> IO.println(l));
                    IO.println("------------------------------------------------------------------------------------------------------");
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

    @Override
    public void changeLogin(String myUser, String myPassword, long otherEmployeeEnrollmentNumber, String oldUserEmployee, String oldPasswordEmployee ,String newUserOtherEmployee, String newPasswordOtherEmployee) {
        boolean isUser = newUserOtherEmployee.matches("[A-Z]{1}([a-z])+");
        boolean isPassword = newPasswordOtherEmployee.matches("[A-Za-z]{1}\\.\\d{6}");

       boolean isLogin = loginList
                .stream()
                .anyMatch(l -> l.user.equals(myUser) && l.password.equals(myPassword) && l.authenticationType.equals(AuthenticationType.LOGIN_MASTER));

     boolean isOtherEmployee = loginList
               .stream()
               .anyMatch(l -> l.employeeEnrollmentNumber == otherEmployeeEnrollmentNumber && l.user.equals(oldUserEmployee) && l.password.equals(oldPasswordEmployee));

       if (isLogin) {
           if (isUser && isPassword) {
               if (isOtherEmployee) {
                   if (!loginList.isEmpty()) {
                       loginList
                               .stream()
                               .filter(l -> l.employeeEnrollmentNumber == otherEmployeeEnrollmentNumber)
                               .forEach(l -> {
                                   l.user = newUserOtherEmployee;
                                   l.password = newPasswordOtherEmployee;
                               });

                   } else {
                       IO.println("-------------------------------------------------------------------");
                       IO.println("Não há logins cadastrados no sistema -> impossível alterar o login.");
                       IO.println("-------------------------------------------------------------------");
                   }
               } else {
                   IO.println("------------------------------------------------------------------------------------------------------");
                   IO.println(String.format(localeBr, "Funcionário matrícula: %d -> inexistente", otherEmployeeEnrollmentNumber));
                   IO.println("------------------------------------------------------------------------------------------------------");
               }
           } else {
               IO.println("------------------------------------------------------------------------------------------------------------------------------");
               IO.println(String.format(localeBr, "Novo usuário: %s e nova senha: %s -> inválidos!", newUserOtherEmployee, newPasswordOtherEmployee));
               IO.println("------------------------------------------------------------------------------------------------------------------------------");
           }
       } else {
           IO.println("-----------------------------------------------------------------------------------------");
           IO.println(String.format(localeBr, "Usuário %s e senha %s -> não conferem.", myUser, myPassword));
           IO.println("-----------------------------------------------------------------------------------------");
       }

    }

    @Override
    public void showAllLogins(String myUser, String myPassword) {
      boolean isMyLogin = loginList
                .stream()
                .anyMatch(l -> l.user.equals(myUser) && l.password.equals(myPassword) && l.authenticationType.equals(LoginUtils.returnAuthenticationType(2)));

      if (isMyLogin) {
          if (!loginList.isEmpty()) {
              IO.println("-------------------------------------------------------------------------------------------------------");
              IO.println("> Lista de Logins <");
              IO.println();

              loginList
                      .forEach(l -> {

                          employeeList
                                  .stream()
                                  .filter(e -> e.getEmployeeEnrollmentNumber() == l.employeeEnrollmentNumber)
                                  .forEach(e -> {
                                      if (l.employeeEnrollmentNumber == e.getEmployeeEnrollmentNumber()) {
                                          IO.println("> Funcionário < ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                                          IO.println(e);
                                          IO.println("> Login < -------------------------------------------------------------------------------------------------------------");
                                          IO.println(l);
                                          IO.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                                          IO.println();
                                      }
                                  });


                      });

              IO.println("-------------------------------------------------------------------------------------------------------");
          } else {
              IO.println("--------------------------------------------");
              IO.println("Logins não cadastrados -> Nada para mostrar.");
              IO.println("--------------------------------------------");
          }
      } else {
          IO.println("-------------------------------------------------------------------------------------------");
          IO.println(String.format(localeBr, "Usuário: %s e senha: %s -> Não conferem.", myUser, myPassword));
          IO.println("-------------------------------------------------------------------------------------------");
      }
    }

    @Override
    public void showSpecificLogin(long otherEmployeeEnrollmentNumber, String myUser, String myPassword) {
        boolean isMyLogin = loginList
                .stream()
                .anyMatch(l -> l.user.equals(myUser) && l.password.equals(myPassword) && l.authenticationType.equals(LoginUtils.returnAuthenticationType(2)));
      boolean isOtherEmployeeEnrollmentNumber = loginList
                .stream()
                .anyMatch(l -> l.employeeEnrollmentNumber == otherEmployeeEnrollmentNumber);

        if (isMyLogin) {
            if (isOtherEmployeeEnrollmentNumber) {
                if (!loginList.isEmpty()) {
                    IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    IO.println("Login encontrado!");
                    IO.println();
                    IO.println("Login:");
                    loginList
                            .stream()
                            .filter(l -> l.employeeEnrollmentNumber == otherEmployeeEnrollmentNumber)
                            .forEach(l -> IO.println(l));
                    IO.println();
                    IO.println("Funcionário:");
                    employeeList
                            .stream()
                            .filter(e -> e.getEmployeeEnrollmentNumber() == otherEmployeeEnrollmentNumber)
                            .forEach(e -> IO.println(e));
                    IO.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                } else {
                    IO.println("-------------------------------------------------------------------------------------------------------");
                    IO.println(String.format(localeBr, "Login da matrícula: %d -> Não encontrado.", otherEmployeeEnrollmentNumber));
                    IO.println("-------------------------------------------------------------------------------------------------------");
                }
            } else {
                IO.println("----------------------------------------------------------------------------------------------");
                IO.println(String.format(localeBr, "Matrícula: %d -> Não encontrada.", otherEmployeeEnrollmentNumber));
                IO.println("----------------------------------------------------------------------------------------------");
            }
        } else {
            IO.println("----------------------------------------------------------------------------------------------");
            IO.println(String.format(localeBr, "Usuário: %s e senha: %s -> Inexistente", myUser, myPassword));
            IO.println("----------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public void rememberLogin(String passwordReminder) {
      boolean isLogin = loginList
                .stream()
                .anyMatch(l -> l.passwordReminder.equals(passwordReminder));

      if (!loginList.isEmpty()) {
          if (isLogin) {
              IO.println("--------------------------------------------------------------------------------------------------------------------------");
              IO.println("Lembrete de senha aceito!");
              IO.println();
              IO.println("Login do funcionário:");
              loginList
                      .stream()
                      .filter(l -> l.passwordReminder == passwordReminder)
                      .forEach(l -> IO.println(l));
              IO.println("--------------------------------------------------------------------------------------------------------------------------");
          } else {
              IO.println(String.format(localeBr, "Lembrete de senha %s -> Inexistente!", passwordReminder));
          }
      } else {
          IO.println("Não há nenhum login cadastrado na lista!");
      }

    }


    @Override
    public void deleteLogin(long otherEmployeeEnrollmentNumber, String myUser, String myPassword) {

     boolean isLoginMaster  = loginList
                .stream()
                .anyMatch(l -> l.user.equals(myUser) && l.password.equals(myPassword) && l.authenticationType.equals(LoginUtils.returnAuthenticationType(2)));

   boolean isOtherEmployeeEnrollmentNumber = loginList
             .stream()
             .anyMatch(l -> l.employeeEnrollmentNumber == otherEmployeeEnrollmentNumber);

     if (isLoginMaster) {
         if (isOtherEmployeeEnrollmentNumber) {
             if (!loginList.isEmpty()) {

               boolean wasDeleted = loginList
                         .removeIf(l -> l.employeeEnrollmentNumber == otherEmployeeEnrollmentNumber);

               if (wasDeleted) {
                   IO.println("------------------------------------------------------------------------------");
                   IO.println(String.format(localeBr, "Login -> Matrícula: %d -> deletado com sucesso!", otherEmployeeEnrollmentNumber));
                   IO.println("------------------------------------------------------------------------------");
               } else {
                   IO.println("----------------------------------------------------------------------------------------");
                   IO.println(String.format(localeBr, "Impossível localizar a matricula: %d, impossível deletar", otherEmployeeEnrollmentNumber));
                   IO.println("----------------------------------------------------------------------------------------");
               }

             } else {
                 IO.println("------------------------------------------");
                 IO.println("Não existem logins cadastrados no sistema.");
                 IO.println("------------------------------------------");
             }
         } else {
             IO.println("------------------------------------------------------------------------------------------------------");
             IO.println(String.format(localeBr, "Login da matrícula %s -> Não encontrado.", otherEmployeeEnrollmentNumber));
             IO.println("------------------------------------------------------------------------------------------------------");
         }
     } else {
         IO.println("-------------------------------------------------------------------------------------------");
         IO.println(String.format(localeBr, "Usuário: %s e senha: %s -> Não conferem.", myUser, myPassword));
         IO.println("-------------------------------------------------------------------------------------------");
     }

    }

    @Override
    public String toString() {
        return String.format(localeBr, "Matrícula: %d | Usuário: %-15s | Senha: %s | Lembrete de Senha: %-8s | Login Type: %s", employeeEnrollmentNumber, user, password, passwordReminder, authenticationType);
    }
}
