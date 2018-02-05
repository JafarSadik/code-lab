package learning.webapps.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
    Disable session creation - but use existing.
 */
public class DisableSessionCreationFilter implements Filter {

    private final static EmptyHttpSession EMPTY_HTTP_SESSION = new EmptyHttpSession();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("Disable session filter");
        request = new HttpServletRequestWrapper(httpServletRequest) {
            @Override
            public HttpSession getSession(boolean create) {
                System.out.println("getSession(boolean)");
                return getSession();
            }

            @Override
            public HttpSession getSession() {
                HttpSession httpSession = httpServletRequest.getSession(false);
                System.out.println("getSession()");
                if (httpSession == null) {
                    System.out.println("-- using empty session");
                    httpSession = EMPTY_HTTP_SESSION;
                } else {
                    System.out.println("-- retrieving existing session");
                }
                return httpSession;
            }

        };
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
