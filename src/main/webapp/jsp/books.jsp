<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="navBar.jsp"%>
<html>
<head>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Title</title>
</head>
<body>

<div class="table_list">
<c:forEach var="book" items="${books}">
    <a href="book?id=${book.id}" methods="get" class="table_list_row">
        <div class="table_list_cell"><c:out value="${book.id}" ></c:out></div>
        <div class="table_list_cell"><c:out value="${book.author}" ></c:out></div>
        <div class="table_list_cell"><c:out value="${book.title}" ></c:out></div>
        <div class="table_list_cell"><c:out value="${book.isbn}" ></c:out></div>
        <div class="table_list_cell"><c:out value="${book.publicationDate.getYear()}"></c:out></div>
    </a><br/>
</c:forEach>
</div>

</body>
</html>
