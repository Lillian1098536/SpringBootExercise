<%--
  Created by IntelliJ IDEA.
  User: ce
  Date: 12/21/17
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<head>
    <title>Employees</title>
</head>
<body>
<h1><spring:message code="label.title" arguments="${}"/></h1>

<ul>
    <c:forEach items="${employees}" var="employee">
        <li>${employee}</li>
    </c:forEach>
</ul>

</body>
</html>
