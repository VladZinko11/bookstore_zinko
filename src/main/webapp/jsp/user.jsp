<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navBar.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    <c:out value="${user.id}"></c:out>
    <c:out value="${user.firstName}"></c:out>
    <c:out value="${user.lastName}"></c:out>
    <c:out value="${user.email}"></c:out>
    <c:out value="${user.role.toString()}"></c:out>
</h1>
</body>
</html>
