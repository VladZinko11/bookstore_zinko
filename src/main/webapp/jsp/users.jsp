<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 15:37
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

<div class="table_list">
    <c:forEach var="user" items="${users}">
        <a href="/controller?command=user&&id=${user.id}" methods="get" class="table_list_row">
            <div class="table_list_cell">${user.id}</div>
            <div class="table_list_cell">${user.firstName}</div>
            <div class="table_list_cell">${user.lastName}</div>
            <div class="table_list_cell">${user.email}</div>
            <div class="table_list_cell">${user.role.toString()}</div>
        </a><br/>
    </c:forEach>
</div>
</body>
</html>
