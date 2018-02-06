package learning.webapps;

public interface WebApplicationConstants {
    interface Request {
        String USER_NAME_PARAMETER_NAME = "userName";
        String PASSWORD_PARAMETER_NAME = "password";
    }

    interface Session {
        String USER_NAME_ATTRIBUTE_NAME = "auth-user";
        String USER_AUTHENTICATION_STATE_ATTRIBUTE_NAME = "auth-state";
    }
}
