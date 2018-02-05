<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Internal application error</title>

    <style type="text/css">
        table {
            font-size: 10px;
        }

        .even {
            background-color: #8fbc8f;
        }

        .odd {
            background-color: #f5f5dc;
        }
    </style>
</head>
<body>
<h1>Internal application error.</h1>
Status code: ${pageContext.errorData.statusCode}<br>
Servlet name: ${pageContext.errorData.servletName}<br>
Request URI: ${pageContext.errorData.requestURI}<br>
<br>
<b>Exception details</b><br>
Messge: ${pageContext.exception.message}

<table>
    <c:forEach var="trace" items="${pageContext.exception.stackTrace}" varStatus="status">
        <tr>
            <c:set var="rowClass" value="even"/>
            <c:if test="${status.index mod 2 == 0}">
                <c:set var="rowClass" value="odd"/>
            </c:if>
            <td class="${rowClass}">${trace}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>