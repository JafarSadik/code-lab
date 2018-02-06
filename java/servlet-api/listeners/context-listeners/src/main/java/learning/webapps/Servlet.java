package learning.webapps;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ServletContext servletContext = getServletContext();
        servletContext.setAttribute("attribute1", 1L);
        servletContext.setAttribute("attribute2", "value");
        servletContext.setAttribute("attribute2", "new value");
        servletContext.removeAttribute("attribute1");
        servletContext.setAttribute("attribute2", null);
        resp.getWriter().print("Request completed, open console to see listener logs");
    }
}
