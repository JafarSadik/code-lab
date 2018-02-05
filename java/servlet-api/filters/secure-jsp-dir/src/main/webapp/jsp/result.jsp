<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Result page</title>
</head>

<c:url var="url" value="/"/>

<body>
    The result is ${result} <br>
    <a href="${url}">Back</a>
</body>
</html>