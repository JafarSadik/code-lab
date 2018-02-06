package learning.webapps;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final HttpSession session = req.getSession(true);
        final Writer out = resp.getWriter();
        resp.setContentType("text/plain");
        if (session.isNew()) {
            System.out.println("Initializing a new session with id: " + session.getId());
            session.setAttribute("session-attribute", "attribute value");
            out.write("A new session was initialized.");
        } else {
            out.write("Session already exists.");
        }
        out.write("It will expire in " + session.getMaxInactiveInterval() + " seconds.");
        out.write("session id: " + session.getId());

        session.setAttribute("bean", new SessionAwareBean());
    }
}
