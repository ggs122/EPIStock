package br.com.login.login;

import java.util.ArrayList;
import java.util.List;

import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.employee.employee.EmployeeImpl;
import br.com.loginInterface.loginInterface.LoginInterface;

public class LoginImpl implements LoginInterface {

    private String user;
    private String password;
    private long employeeEnrollmentNumber;
    private String passwordReminder;

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

    public String getPasswordReminder() {
        return passwordReminder;
    }

    @Override
    public void createLogin(long employeeEnrollmentNumber, String user, String password, String passwordReminder) {

      boolean isEmployee = employeeList
                .stream()
                        .anyMatch(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber);

        System.out.println(isEmployee);

      boolean isUser = user.matches("[A-Z]{1}([a-z])+");
      boolean isPassword = password.matches("[A-Za-z]{1}\\.\\d{6}");

      if (isEmployee && isUser && isPassword) {
          LoginImpl login = new LoginImpl(employeeEnrollmentNumber, user,password, passwordReminder);
          loginList.add(login);
      }

    }

    @Override
    public void showLogins() {
        loginList
                .stream()
                .forEach(l -> IO.println(l));
    }

    @Override
    public String toString() {
        return "Login{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", employeeEnrollmentNumber=" + employeeEnrollmentNumber +
                ", passwordReminder='" + passwordReminder + '\'' +
                '}';
    }
}
