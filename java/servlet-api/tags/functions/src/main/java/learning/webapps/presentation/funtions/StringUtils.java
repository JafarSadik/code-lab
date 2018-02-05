package learning.webapps.presentation.funtions;


public class StringUtils {
    public static final String ENDING = " ...";

    public static String shorten(String str, int maxLength) {
        return str.length() > maxLength ? str.substring(0, maxLength) + ENDING : str;
    }
}
