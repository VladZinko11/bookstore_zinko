<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="user_name"><c:out value="${user.firstName}"></c:out>
        <c:out value="${user.lastName}"></c:out></div>
    <div class="user_id">id = <c:out value="${user.id}"></c:out></div>
    <div class="user_email"><c:out value="${user.email}"></c:out></div>
</div>
</body>
</html>
