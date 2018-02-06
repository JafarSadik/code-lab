package learning.webapps;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final PrintWriter out = resp.getWriter();
        final String initParam = getInitParameter("user-name");
        final String msg = "Hello world " + initParam + " :)";
        out.print(msg);
        resp.setContentType("text/html");
    }
}
