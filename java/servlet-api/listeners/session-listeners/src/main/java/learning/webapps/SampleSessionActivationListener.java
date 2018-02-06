package learning.webapps;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/*
   Listener probably won't be invoked as web application is not distributed.
*/
public class SampleSessionActivationListener implements HttpSessionActivationListener {
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println(":: session will passivate");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println(":: session did activate");
    }
}
