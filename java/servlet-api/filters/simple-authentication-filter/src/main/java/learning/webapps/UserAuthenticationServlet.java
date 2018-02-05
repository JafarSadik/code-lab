package learning.webapps;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserAuthenticationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String user = req.getParameter(WebApplicationConstants.Request.USER_NAME_PARAMETER_NAME);
        final String password = req.getParameter(WebApplicationConstants.Request.PASSWORD_PARAMETER_NAME);
        String redirectTo = req.getContextPath();
        if (validateCredentials(user, password)) {
            //If user & password correct create session
            final HttpSession httpSession = req.getSession();
            httpSession.setAttribute(WebApplicationConstants.Session.USER_AUTHENTICATION_STATE_ATTRIBUTE_NAME, AuthenticationState.AUTHENTICATED);
            httpSession.setAttribute(WebApplicationConstants.Session.USER_NAME_ATTRIBUTE_NAME, user);
            redirectTo = req.getContextPath() + "/protected/file.txt";
            System.out.println("User authenticated");
        } else {
            System.out.println("User authentication failure");
        }
        System.out.println("Redirecting to context: " + redirectTo);
        resp.sendRedirect(redirectTo);
    }

    private boolean validateCredentials(String user, String password) {
        return user != null &&
                password != null &&
                user.equals("admin") &&
                password.equals("admin");
    }
}
