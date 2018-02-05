package learning.webapps;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<Shop> shops = new LinkedList<Shop>();
        shops.add(new Shop("PWN", "Poland", "Warsaw", "http://www.pwn.pl"));
        shops.add(new Shop("Helion", "Poland", "Warsaw", "http://helion.pl"));
        shops.add(new Shop("Wydaje", "Poland", "Katowice", "http://wydaje.pl"));
        shops.add(new Shop("Amazon", "USA", "Nevada", "http://www.amazon.com"));
        req.setAttribute("shops", shops);
        req.getRequestDispatcher("/pages/shops.jsp").forward(req, resp);
    }
}
