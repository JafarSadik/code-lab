package learning.webapps.presentation.tags;


import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class URLTag extends SimpleTagSupport {

    private String value;

    private String var;

    public void setValue(String value) {
        this.value = value;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public void doTag() throws JspException, IOException {
        final JspFragment body = getJspBody();
        final JspContext context = getJspContext();
        final JspTag parent = getParent();
        final JspWriter writer = context.getOut();
        final String url = transformURL(value);

        if (var != null) {
            context.setAttribute(var, url, PageContext.PAGE_SCOPE);
        } else {
            writer.write(url);
        }

    }

    private String transformURL(String url) {
        if (url.startsWith("/")) {
            String contextPath = (String) getJspContext().findAttribute("contextPath");
            return contextPath + url;
        } else {
            return url;
        }
    }
}
