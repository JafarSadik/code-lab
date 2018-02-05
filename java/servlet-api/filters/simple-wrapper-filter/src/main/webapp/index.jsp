<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Session management</title>
</head>
<body>
<c:url var="create" value="/create"/>
<c:url var="destroy" value="/destroy"/>

<a href="${create}">Create session</a><br>
<a href="${destroy}">Destroy session</a><br>

<c:if test="${pageContext.session eq null}">
    <b>Session doesn't exist</b>
</c:if>

<br>

<c:if test="${not empty(result)}">
    <p>
        Message:
        <c:choose>
            <c:when test="${result == 'Created'}">Session created properly</c:when>
            <c:when test="${result eq 'Destroyed'}">Session destroyed</c:when>
            <c:when test="${result eq 'AlreadyExists'}">Session already exists</c:when>
            <c:when test="${result eq 'NoSession'}">There is no session that can be destroyed</c:when>
        </c:choose>
    </p>
</c:if>

<p>
    Session attributes:
    <c:forEach var="attr" items="${pageContext.session.attributeNames}" varStatus="status">
        ${attr} <c:if test="${not status.last}">,</c:if>
    </c:forEach>
</p>
</body>
</html>