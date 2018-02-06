package learning.webapps.filters.cache;


import learning.webapps.DynamicResourceServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IfNoneMatchHeaderFilter implements Filter {
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
            if (httpGet) {
                setCachingHeaders(httpServletResponse);
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private void setCachingHeaders(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("ETag", DynamicResourceServlet.eTag);
    }

    private boolean resourceModified(HttpServletRequest httpServletRequest) {
        final String etag = httpServletRequest.getHeader("If-None-Match");
        return etag == null || !etag.equals(DynamicResourceServlet.eTag);
    }
}
