<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="pageTitle" required="true" rtexprvalue="true" %>
<%@ attribute name="footer" required="true" rtexprvalue="false" %>

<c:url var="cssURL" value="/static/test.css"/>

<html>
<head>
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="${cssURL}"/>
</head>
<body>
<div class="menu-container">
    <%@ include file="menu.html" %>
</div>

<div class="body-container">
    <jsp:doBody/>
</div>

<div class="footer-container">
    ${footer}
</div>
</body>
</html>