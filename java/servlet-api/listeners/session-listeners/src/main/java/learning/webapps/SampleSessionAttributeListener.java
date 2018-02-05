package learning.webapps;


import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SampleSessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println(":: Attribute added to session with id: " + se.getSession().getId() + ", attribute: " + se.getName());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println(":: Attribute removed from session with id: " + se.getSession().getId() + ", attribute: " + se.getName());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println(":: Attribute replaced in session with id: " + se.getSession().getId() + ", attribute: " + se.getName());
    }
}
