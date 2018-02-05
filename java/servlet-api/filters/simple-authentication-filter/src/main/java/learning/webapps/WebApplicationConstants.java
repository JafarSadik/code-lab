package learning.webapps;

public interface WebApplicationConstants {

    public static interface Request {
        public final static String USER_NAME_PARAMETER_NAME = "userName";
        public final static String PASSWORD_PARAMETER_NAME = "password";
    }

    public static interface Session {
        public final static String USER_NAME_ATTRIBUTE_NAME = "auth-user";
        public final static String USER_AUTHENTICATION_STATE_ATTRIBUTE_NAME = "auth-state";
    }
}
