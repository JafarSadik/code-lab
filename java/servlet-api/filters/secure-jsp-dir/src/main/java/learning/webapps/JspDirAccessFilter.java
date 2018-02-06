package learning.webapps;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    This filter breaks filter chain and redirects to main application context.
 */
public class JspDirAccessFilter implements Filter {
    private String mainContext = "/";

    @Override
    public void init(FilterConfig filterConfig) {
        mainContext = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(mainContext);
    }

    @Override
    public void destroy() {

    }
}
