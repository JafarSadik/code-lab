package learning.webapps.filters.cache;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExpiresHeaderFilter implements Filter {

    public final static int CACHE_AGE_SECONDS = 60 * 60 * 24 * 60;//60 days given in seconds

    public final static int CACHE_AGE_MILLISECONDS = CACHE_AGE_SECONDS * 1000;//60 days given in milliseconds

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) request).getMethod().equalsIgnoreCase("GET")) {
            setCachingHeaders((HttpServletResponse) response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private void setCachingHeaders(HttpServletResponse httpServletResponse) {
        long now = System.currentTimeMillis();
        httpServletResponse.setDateHeader("Date", now);
        httpServletResponse.setDateHeader("Expires", now + CACHE_AGE_MILLISECONDS);
    }
}
