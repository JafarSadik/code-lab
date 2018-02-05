<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error message</title>
</head>
<body>
<h2>Error occurred while processing file upload</h2>
<p>
    Message: ${pageContext.exception.message}
</p>
</body>
</html>