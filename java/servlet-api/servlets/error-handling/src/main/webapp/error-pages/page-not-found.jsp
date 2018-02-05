<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Page not found</title>
</head>
<body>
<h1>We are sorry, couldn't find page that you have requested.</h1>
Status code: ${pageContext.errorData.statusCode}<br>
Servlet name: ${pageContext.errorData.servletName}<br>
Request URI: ${pageContext.errorData.requestURI}<br>
</body>
</html>