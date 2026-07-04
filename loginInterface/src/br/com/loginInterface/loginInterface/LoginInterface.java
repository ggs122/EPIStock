package br.com.loginInterface.loginInterface;

public interface LoginInterface {

    void createLogin(long employeeEnrollmentNumber, String user, String password, String passwordReminder, int authenticationType);
    void changeLogin(String myUser, String myPassword, long otherEmployeeEnrollmentNumber, String oldUserEmployee, String oldPasswordEmployee ,String newUserOtherEmployee, String newPasswordOtherEmployee);
    void showLogins(String user, String password);

}
