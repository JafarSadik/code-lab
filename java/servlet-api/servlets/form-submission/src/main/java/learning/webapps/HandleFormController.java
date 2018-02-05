package learning.webapps;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.System.out;


public class HandleFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out.println(":: HandleFormController:doGet");
        handleParameters(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out.println(":: HandleFormController.doPost");
        handleParameters(req, resp);
    }

    private void handleParameters(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String processed = req.getParameter("param").trim();
        final String context = req.getContextPath();
        resp.sendRedirect(context + "/submit.jsp?processed=" + processed);
    }
}
