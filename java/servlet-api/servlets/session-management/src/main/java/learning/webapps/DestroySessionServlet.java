package learning.webapps;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DestroySessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        SessionStatus result = null;
        if (session != null) {
            session.invalidate();
            System.out.println("Destroying session");
            result = SessionStatus.Destroyed;
        } else {
            System.out.println("Session doesn't exist");
            result = SessionStatus.NoSession;
        }

        req.setAttribute("result", result);
        req.getRequestDispatcher(req.getContextPath() + "/index.jsp").forward(req, resp);
    }
}
