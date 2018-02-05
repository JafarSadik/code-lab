<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<c:url var="cacheControlCachingURL" value="/cached/cache-control-header-sample"/>
<c:url var="expiresCachingURL" value="/cached/expires-header-sample"/>
<c:url var="ifModifiedSinceCachingURL" value="/cached/if-modified-since-header-sample"/>
<c:url var="ifNoneMatchCachingURL" value="/cached/if-none-match-header-sample"/>
<c:url var="staticResourceURL" value="/image/static-resource"/>
<c:url var="modifyResourceURL" value="/cached/reset"/>

<html>
<head>
    <title>Choose resource</title>
</head>
<body>
<p>
    Choose resource:
</p>
<ul>
    <li>
        <a href="${staticResourceURL}">Static resource cached by a container</a>
    </li>
    <li>
        Dynamic resource caching handled by filter. There are several ways of doing it:
        <ul>
            <li>
                <a href="${cacheControlCachingURL}">Cache-Control header</a>
            </li>
            <li>
                <a href="${expiresCachingURL}">Expires header </a>
            </li>
            <li>
                <a href="${ifModifiedSinceCachingURL}">If-modified-since header</a>
            </li>
            <li>
                <a href="${ifNoneMatchCachingURL}">Tag & If-none-match headers</a>
            </li>
        </ul>
    </li>
    <p style="font-style: italic;">
        Cache-control & Expires headers are useful for caching not often changing resources.
        After sending back those headers web browser won't ask for them for a specified period of time so one has to be
        careful using this method. According to yahoo best practices it is good to set Expires header to a big value -
        lets say
        now + 2 years. When releasing new version of application naming of static resources should change during build
        process
        so it will no longer be cached.
    </p>
    <p style="font-style: italic;">
        If-modified-since & If-none-match headers are suitable for conditional caching of dynamic resources.
        Both methods require HTTP status respond code 304 "NON MODIFIED" in case resource hasn't been modified.
    </p>
    <form action="${modifyResourceURL}" method="POST">
        <p>
            <input type="submit" value="Modify resource">
        </p>
    </form>
</ul>

</body>
</html>