package learning.webapps;


import javax.servlet.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Filter2 implements Filter {

    private String message;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        message = filterConfig.getInitParameter("message");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        addMessage(request);
        chain.doFilter(request, response);
    }

    private void addMessage(ServletRequest request) {
        List<String> list = (List) request.getAttribute("messageList");
        if (list == null) {
            list = new LinkedList<String>();
        }
        list.add(message);
        request.setAttribute("messageList", list);
    }

    @Override
    public void destroy() {

    }
}
