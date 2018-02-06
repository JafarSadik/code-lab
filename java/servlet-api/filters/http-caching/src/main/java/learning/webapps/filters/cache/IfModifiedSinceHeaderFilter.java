package learning.webapps.filters.cache;


import learning.webapps.DynamicResourceServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IfModifiedSinceHeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        final boolean httpGet = httpServletRequest.getMethod().equalsIgnoreCase("GET");

        if (httpGet && !resourceModified(httpServletRequest)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        } else {
            if (httpGet) setCachingHeaders(httpServletResponse);
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private void setCachingHeaders(HttpServletResponse httpServletResponse) {
        long now = System.currentTimeMillis();
        httpServletResponse.setDateHeader("Date", now);
        httpServletResponse.setDateHeader("Last-Modified", now);
    }

    private boolean resourceModified(HttpServletRequest request) {
        final String IF_MODIFIED_SINCE = "If-Modified-Since";
        return request.getHeader(IF_MODIFIED_SINCE) == null ||
                request.getDateHeader(IF_MODIFIED_SINCE) < DynamicResourceServlet.lastModified;
    }
}
