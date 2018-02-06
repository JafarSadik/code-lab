<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Usage of simple tags</title>
</head>
<body>
<p>
<p>Content boxing tag (sample 1/2)</p>
<%-- Body content defined in a standard way --%>
<t:box borderColor="blue">
    This is sample content that should be placed in blue box.
</t:box>
</p>

<p>
<p>Content boxing tag (sample 2/2)</p>
<%-- Body content enclosed in jsp:body tag becouse jsp:attribute is used. --%>
<t:box>
    <jsp:attribute name="borderColor">red</jsp:attribute>
    <jsp:body>And this one should be wrapped in red box.</jsp:body>
</t:box>
</p>

<hr/>

<p>
    <a href="pages/layout.jsp">Click to see layout tag in action</a>
</p>

</body>
</html>