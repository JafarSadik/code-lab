package learning.webapps.presentation.tags;


import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ContentBoxingTag extends SimpleTagSupport {
    public final static String DEFAULT_BORDER_COLOR = "black";
    private String borderColor;

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getBorderColor() {
        return borderColor == null ? DEFAULT_BORDER_COLOR : borderColor;
    }

    @Override
    public void doTag() throws JspException, IOException {
        final JspFragment body = getJspBody();
        final JspContext context = getJspContext();
        final JspTag parent = getParent();
        final String borderColor = getBorderColor();
        final JspWriter writer = context.getOut();
        writer.write("<div " + "style='border-width:1px;border-style:solid;border-color:" + borderColor + ";'>");
        body.invoke(writer);
        writer.write("</div>");
    }
}
