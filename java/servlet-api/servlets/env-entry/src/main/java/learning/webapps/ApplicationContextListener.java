package learning.webapps;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;

public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Context context = new InitialContext();
            Context javaEnvContext = (Context) context.lookup("java:comp/env/");
            String remoteAddress = (String) javaEnvContext.lookup("remote-address"); //java:comp/env/remote-address
            sce.getServletContext().setAttribute("remoteAddress", remoteAddress);
        } catch (NamingException exc) {
            exc.printStackTrace();
        }
    }
}
