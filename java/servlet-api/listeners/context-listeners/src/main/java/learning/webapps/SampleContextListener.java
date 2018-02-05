package learning.webapps;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SampleContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(":: Servlet context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(":: Servlet context destroyed");
    }
}
