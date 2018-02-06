package learning.webapps;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private final static boolean GET_SESSION_ONLY_IF_EXISTS = false;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final HttpSession httpSession = httpServletRequest.getSession(GET_SESSION_ONLY_IF_EXISTS);
        AuthenticationState authState = null;
        if (httpSession != null) {
            authState = (AuthenticationState) httpSession.getAttribute(WebApplicationConstants.Session.USER_AUTHENTICATION_STATE_ATTRIBUTE_NAME);
        }

        if (authState != null && authState == AuthenticationState.AUTHENTICATED) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {

    }
}
