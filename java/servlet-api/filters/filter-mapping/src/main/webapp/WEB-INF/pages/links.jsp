<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>
    Sample links that may be used in order to invoke filters:
</p>

<ol>
    <c:forEach var="p" items="${requestScope['pages']}">
        <c:url var="url" value="${p}"/>
        <li>
            <a href="${url}">${url}</a>
        </li>
    </c:forEach>
</ol>
