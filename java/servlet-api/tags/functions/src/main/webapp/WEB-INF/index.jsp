<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fu" uri="http://mycompany.com/utils/functions" %>

<html>
<head>
    <title>Usage of custom function</title>
</head>
<body>
<p>
    'Hello world' shortened to 5 characters: <b>${fu:shorten('Hello world', 5)}</b>
</p>
</body>
</html>