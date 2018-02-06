package learning.webapps;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DefaultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter out = new PrintWriter(resp.getWriter());
        final String servletPath = req.getServletPath();
        final String requestURI = req.getRequestURI();
        final String queryString = req.getQueryString();
        final String contextPath = req.getContextPath();
        final String pathInfo = req.getPathInfo();
        final String servletName = getServletName();
        resp.setContentType("text/html");
        out.println("<html><body>");
        out.println("context-path: " + contextPath + "<br>");
        out.println("servlet-path: " + servletPath + "<br>");
        out.println("servlet-name: " + servletName + "<br>");
        out.println("request-uri: " + requestURI + "<br>");
        out.println("query-string: " + queryString + "<br>");
        out.println("path-info: " + pathInfo + "<br>");

        //Print messages added by visiting filters
        out.println("<br><hr>");
        final List<String> messages = (List) req.getAttribute("messageList");
        if (messages == null) {
            out.println("No filter was applied!" + "<br>");
        } else {
            for (String message : messages) {
                out.println(message + "<br>");
            }
        }

        //Print links
        out.println("<br><hr>");
        req.setAttribute("pages",
                new String[]{
                        "/test.ext",
                        "/test.ext?hello=5",
                        "/resources/test",
                        "/resources/test/abc?hello=abc",
                        "/other",
                        "/other/2?param=4"}
        );
        final String url = resp.encodeURL("/links.jsp");
        req.getRequestDispatcher(url).include(req, resp);

        out.println("</body></html>");
    }
}
