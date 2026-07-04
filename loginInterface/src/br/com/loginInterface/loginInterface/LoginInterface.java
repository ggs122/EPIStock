package br.com.loginInterface.loginInterface;

public interface LoginInterface {

    void createLogin(long employeeEnrollmentNumber, String user, String password, String passwordReminder);
    void showLogins(String user, String password);

}
