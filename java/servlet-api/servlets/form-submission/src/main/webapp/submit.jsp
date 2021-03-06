<%@ page contentType="text/html" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Form submission</title>
    <style type="text/css">
        table {
            border: thin dashed black;
            padding: 0.5em;
            width: 15em;
            height: 10em;
            margin: 2em;
            clear: both;
        }

        td {
            width: 50%;
        }

        p {
            text-align: center;
        }

        button {
            float: right;
        }

    </style>
</head>
<body>

<c:if test="${not empty(param.processed)}">
    Trimmed text: <b>${param.processed}</b>
</c:if>

<c:url var="submitUrl" value="/submit"/>

<form action="${submitUrl}" method="GET">
    <table>
        <tbody>
        <tr>
            <td colspan="2">
                <p>Send form using GET</p>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="param">
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Submit</button>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<form action="${submitUrl}" method="POST">
    <table>
        <tbody>
        <tr>
            <td colspan="2">
                <p>Send form using POST</p>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="param">
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Submit</button>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>