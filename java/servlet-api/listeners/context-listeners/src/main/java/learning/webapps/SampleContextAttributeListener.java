package learning.webapps;


import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class SampleContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent srae) {
        System.out.println("Context attribute added: " + srae.getName() + ", value: " + srae.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent srae) {
        System.out.println("Context attribute removed: " + srae.getName());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent srae) {
        System.out.println("Context attribute replaced: " +
                srae.getName() + ", old value: " +
                srae.getValue() + ", new value: " +
                srae.getServletContext().getAttribute(srae.getName())
        );
    }
}
