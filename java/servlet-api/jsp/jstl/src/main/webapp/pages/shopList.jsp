<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<table>
    <tr>
        <th>
            <fmt:message key="shop.list.name"/>
        </th>
        <th>
            <fmt:message key="shop.list.address"/>
        </th>
        <th>
            <fmt:message key="shop.list.url"/>
        </th>
    </tr>
    <c:forEach var="shop" items="${shops}" varStatus="status">
        <c:set var="cssStyle" value="background:white;"/>
        <c:if test="${status.index % 2 eq 0}">
            <c:set var="cssStyle" value="background:gray;"/>
        </c:if>

        <tr>
            <td style="${cssStyle}">
                    ${shop.name}
            </td>
            <td style="${cssStyle}">
                    ${fn:toUpperCase(shop.address)}, ${fn:toUpperCase(shop.city)}
            </td>
            <td style="${cssStyle}">
                <a href="${shop.url}">${shop.url}</a>
            </td>
        </tr>
    </c:forEach>
</table>