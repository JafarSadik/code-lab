package learning.webapps;


import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


public class SessionAwareBean implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("Object added to session: " + this);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("Object removed from session: " + this);
    }
}
