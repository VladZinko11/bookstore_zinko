<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navBar.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Title</title>
</head>
<body>

<div class="user">
    <div class="user_name">${user.firstName} ${user.lastName}</div>
    <div class="user_id">id = ${user.id}</div>
    <div class="user_email">${user.email}</div>
</div>
</body>
</html>
