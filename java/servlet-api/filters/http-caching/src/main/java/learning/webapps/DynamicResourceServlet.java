package learning.webapps;

import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;


/*
 * Made to emulate heavy weight resource invocation - in case response not cached
 * there should be 5 second delay while loading page
 */
public class DynamicResourceServlet extends HttpServlet {

    public static long lastModified = getCurrentTimeMs();
    public static String eTag = randomETag();

    /**
     * Get request to retrieve resource
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Writer out = resp.getWriter();
        out.write("Dynamically generated content\n");
        out.write("Content created at " + new Date());

        //Emulate content generation by adding some delay
        delay(5000);

        out.flush();
        out.close();
    }

    /**
     * Post request to alter resource modification date and ETag.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String context = req.getContextPath() + "/";
        lastModified = getCurrentTimeMs();
        eTag = randomETag();
        resp.sendRedirect(context);
    }


    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException iExc) {
            iExc.printStackTrace();
        }
    }

    private static long getCurrentTimeMs() {
        return System.currentTimeMillis();
    }

    private static String randomETag() {
        return RandomStringUtils.randomAlphabetic(16);
    }
}
