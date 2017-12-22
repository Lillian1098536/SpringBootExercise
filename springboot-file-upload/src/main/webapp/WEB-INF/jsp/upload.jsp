<%--
  Created by IntelliJ IDEA.
  User: ce
  Date: 12/21/17
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring Boot File Upload</title>
</head>
<body>
<h1>Upload Files</h1>
<form method="POST" action="upload" enctype="multipart/form-data">
    <input type="file" name="file"><br/><br/>
    <input type="submit" name="submit">
</form>
</body>
</html>
