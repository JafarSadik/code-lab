package learning.webapps;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("attribute1", 1L);
        req.setAttribute("attribute2", "value");
        req.setAttribute("attribute2", "new value");
        req.removeAttribute("attribute1");
        req.setAttribute("attribute2", null);
        resp.getWriter().print("Request completed, open console to see listener logs");
    }
}
