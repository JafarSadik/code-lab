package learning.webapps;


import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class SampleRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request initialized");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request destroyed");
    }
}
