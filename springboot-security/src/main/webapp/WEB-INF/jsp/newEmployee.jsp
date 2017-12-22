<%--
  Created by IntelliJ IDEA.
  User: ce
  Date: 12/21/17
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>New Employee</title>
</head>
<body>
<h1>Add New Employee</h1>

<form:form modelAttribute="form">
    <form:errors path="" element="div" />
    <div>
        <form:label path="name">Name</form:label>
        <form:input path="name" />
        <form:errors path="name" />
    </div>
    <div>
        <input type="submit" />
    </div>
</form:form>

</body>
</html>
