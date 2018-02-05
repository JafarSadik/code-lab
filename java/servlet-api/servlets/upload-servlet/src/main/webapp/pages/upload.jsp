<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Upload</title>
</head>
<body>
<form action="upload" method="POST" enctype="multipart/form-data">
    <h2>Upload file</h2>
    <p>
        Short description
        <input name="description" type="input" size="100">
        <input name="file" type="file">
    </p>
    <p>
        <input type="submit" value="Upload">
    </p>

</form>

<h3>List of uploaded files</h3>
<table>
    <thead>
    <th></th>
    <th>File name</th>
    <th>Content type</th>
    <th>File size</th>
    <th>Uploaded to memory</th>
    <th>Description</th>
    </thead>

    <c:forEach var="file" items="${sessionScope['uploaded-items']}" varStatus="varStatus">
        <tr>
            <td>${varStatus.index}</td>
            <td>${file.fileName}</td>
            <td>${file.contentType}</td>
            <td>${file.fileSize}</td>
            <td>${file.uploadToMemory}</td>
            <td>${file.description}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>