package br.com.login.login;

public final class LoginUtils {

    private LoginUtils(){}

    public static LoginImpl.AuthenticationType returnAuthenticationType(int chooseAuthenticationType) {
        return switch (chooseAuthenticationType) {
            case 1 -> LoginImpl.AuthenticationType.LOGIN;
            case 2 -> LoginImpl.AuthenticationType.LOGIN_MASTER;
            default -> LoginImpl.AuthenticationType.INEXISTENTE;
        };
    }

}
