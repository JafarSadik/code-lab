package learning.webapps.presentation.tags.listeners;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AddPageContext implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent e) {
        final ServletContext context = e.getServletContext();
        final String contextPath = context.getContextPath();
        context.setAttribute("contextPath", contextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent e) {

    }
}
