<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="borderColor" required="true" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="style" value="border-style:solid;border-color:${borderColor};"/>
<div style="${style}">
    <jsp:doBody/>
</div>