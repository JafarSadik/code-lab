<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="en" scope="request"/>
<fmt:setBundle basename="lang" scope="request"/>

<html>
<head>
    <title><fmt:message key="shops.list.title"/></title>
</head>
<body>

<div>
    <fmt:message key="shops.available.online"/>
</div>

<div>
    <c:import url="shopList.jsp">
        <c:param name="ref" value="http://www.wp.pl"/>
    </c:import>
</div>

<hr/>

<div>
    <%@include file="footer.html" %>
</div>

</body>
</html>