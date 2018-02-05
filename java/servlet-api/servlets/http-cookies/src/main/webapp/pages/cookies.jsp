<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Cookies</title>
    <script src="scripts/all.js">
    </script>

    <style type="text/css">
        .tip {
            text-decoration-color: #a9a9a9;
            text-decoration-style: dashed;
            font-size: small;
        }

    </style>
</head>

<body>
Cookies:<br>
<ol>
    <c:forEach var="c" items="${cookie}">
        <li>
            name[${c.key}],
            value[${c.value.value}],
            domain[${c.value.domain}],
            comment[${c.value.comment}],
            maxAge[${c.value.maxAge}],
            path[${c.value.path}],
            secure[${c.value.secure}],
            version[${c.value.version}]
        </li>
    </c:forEach>
</ol>

<hr>
Add new cookie:<br>
<form action="cookies" method="POST">
    <p>
        Cookie name:
        <input id="cookie-name"
               name="cookie-name"
               class="tip"
               autocomplete="off"
               value="type cookie name here"
               onclick="turnOffTip(this.id);">
    </p>

    <p>
        Cookie value:
        <input id="cookie-value"
               name="cookie-value"
               class="tip"
               autocomplete="off"
               value="type cookie value here"
               onclick="turnOffTip(this.id);">
    </p>

    <p>
        Max age:
        <input id="cookie-max-age"
               name="cookie-max-age"
               class="tip"
               autocomplete="off"
               value="type max age here"
               onclick="turnOffTip(this.id);">
    </p>

    <button type="submit">Add cookie</button>
</form>
</body>
</html>