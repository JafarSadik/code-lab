<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="rootURL" value="/"/>

<l:layout>
    <jsp:attribute name="pageTitle">Simple page rendered using layout tag</jsp:attribute>
    <jsp:attribute name="footer">All rights reserved</jsp:attribute>
    <jsp:body>
        <p>
            Some body content may be really long ...
        </p>
        <p>
            <a href="${rootURL}">Go back to main page</a>
        </p>
    </jsp:body>
</l:layout>