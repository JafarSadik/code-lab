package learning.webapps.presentation.tags;


import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class ForEachTag extends SimpleTagSupport {

    private String varName;

    private String statusVarName;

    private List items;

    public void setVar(String var) {
        this.varName = var;
    }

    public void setStatusVar(String statusVar) {
        this.statusVarName = statusVar;
    }

    public void setItems(List items) {
        this.items = items;
    }

    @Override
    public void doTag() throws JspException, IOException {
        final JspFragment body = getJspBody();
        final JspContext context = getJspContext();
        final JspTag parent = getParent();
        final JspWriter writer = context.getOut();
        final ForEachStatusVar statusVar = new ForEachStatusVar();
        boolean first = true;
        boolean last = false;
        int index = 0;

        for (Object item : items) {
            context.setAttribute(varName, item, PageContext.PAGE_SCOPE);
            statusVar.setFirst(first);
            statusVar.setLast(last);
            statusVar.setIndex(index);
            context.setAttribute(statusVarName, statusVar, PageContext.PAGE_SCOPE);

            first = false;
            if (index++ >= items.size()) {
                last = true;
            }
            body.invoke(null);
        }
    }
}
