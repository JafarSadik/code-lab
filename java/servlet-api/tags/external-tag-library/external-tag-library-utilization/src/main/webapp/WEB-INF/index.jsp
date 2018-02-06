<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sl" uri="http://mycompany.com/simple-tag-lib" %>

<html>
<head>
    <title>Usage of simple tags</title>
</head>
<body>
<p>
<p>Content boxing tag (sample 1/2)</p>
<%-- Body content defined in a standard way --%>
<sl:box borderColor="blue">
    This is sample content that should be placed in blue box.
</sl:box>
</p>

<p>
<p>Content boxing tag (sample 2/2)</p>
<%-- Body content enclosed in jsp:body tag becouse jsp:attribute is used. --%>
<sl:box>
    <jsp:attribute name="borderColor">red</jsp:attribute>
    <jsp:body>And this one should be wrapped in red box.</jsp:body>
</sl:box>
</p>

<hr/>

<%
    /*
        Adding items to page context. It is a quick & dirty solution as scripting introduces hard to read and maintain pages.
        In real life applications attributes should be added by some kind of controller, listener or filter.
     */
    List<String> items = new LinkedList<String>();
    items.add("one");
    items.add("two");
    items.add("three");
    items.add("four");
    request.setAttribute("items", items);
%>

<p>
<p>ForEachTag sample</p>
<%-- Sample usage of for each tag --%>
<table>
    <tr>
        <th>Index</th>
        <th>Value</th>
    </tr>
    <sl:foreach var="item" items="${items}" statusVar="status">
        <tr>
            <td>${status.index}</td>
            <td>${item}</td>
        </tr>
    </sl:foreach>
</table>
</p>

<hr/>

<p>
<p>URL tag sample</p>
<p>
    Url for /resources/icon.png is: <b><sl:url value="/resources/icon.png"/></b>
</p>

<p>
    Url for http://www.wp.pl is: <b><sl:url value="http://www.wp.pl"/></b>
</p>

<p>
    <sl:url var="resourceURL" value="resources/icon.png"/>
    Url for resources/icon.png is: <b>${resourceURL}</b>
</p>
</p>

<hr/>

</body>
</html>