package learning.webapps;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionStatus result = null;

        if (req.getSession().isNew()) {
            System.out.println("Created new session");
            result = SessionStatus.Created;
            addSampleSessionAttributes(req.getSession());
        } else {
            System.out.println("Session already exists");
            result = SessionStatus.AlreadyExists;
        }

        req.setAttribute("result", result);
        req.getRequestDispatcher(req.getContextPath() + "/index.jsp").forward(req, resp);
    }

    private void addSampleSessionAttributes(HttpSession httpSession) {
        for (int i = 0; i < 10; i++) {
            httpSession.setAttribute("attribute no " + i, i);
        }
    }
}
