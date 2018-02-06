package learning.webapps;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionThrowingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        dangerousCode();
    }

    private void dangerousCode() {
        throw new RuntimeException("Something went wrong.");
    }
}
