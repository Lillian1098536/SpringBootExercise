<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Student Login</title>
</head>
<body>

<h1><strong>Student Login</strong></h1>

<c:url value="/login" var="login"/>

<form:form action="${login}" method="post">
    <label>Username:</label> <input type="text" name="username"/>
    <label>Password:</label> <input type="text" name="password"/>
    <input type="submit"/>
</form:form>

</body>
</html>
