<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="navBar.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Title</title>
</head>
<body>

<div class="table_list">
    <c:forEach var="user" items="${users}">
        <a href="user?id=${user.id}" methods="get" class="table_list_row">
            <div class="table_list_cell"><c:out value="${user.id}"></c:out></div>
            <div class="table_list_cell"><c:out value="${user.firstName}"></c:out></div>
            <div class="table_list_cell"><c:out value="${user.lastName}"></c:out></div>
            <div class="table_list_cell"><c:out value="${user.email}"></c:out></div>
            <div class="table_list_cell"><c:out value="${user.role.toString()}"></c:out></div>
        </a><br/>
    </c:forEach>
</div>
</body>
</html>
