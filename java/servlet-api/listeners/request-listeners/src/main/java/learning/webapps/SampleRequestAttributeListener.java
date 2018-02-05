package learning.webapps;


import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class SampleRequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("Request attribute added: " + srae.getName() + ", value: " + srae.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("Request attribute removed: " + srae.getName());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("Request attribute replaced: " +
                srae.getName() + ", old value: " +
                srae.getValue() + ", new value: " +
                srae.getServletRequest().getAttribute(srae.getName())
        );
    }
}
