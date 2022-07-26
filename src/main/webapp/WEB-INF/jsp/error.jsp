<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        <fmt:message key="head.error"/>
    </title>
</head>
<body>
<c:if test="${not empty requestScope.error}">
        <span>
                ${requestScope.error}
        </span>
</c:if>
</body>
</html>
