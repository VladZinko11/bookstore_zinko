<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navBar.jsp"%>
<html>
<head>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Title</title>
</head>
<body>

<div class="table_list">
<c:forEach var="book" items="${books}">
    <a href="/controller?command=book&&id=${book.id}" methods="get" class="table_list_row">
        <div class="table_list_cell">${book.id}</div>
        <div class="table_list_cell">${book.author}</div>
        <div class="table_list_cell">${book.title}</div>
        <div class="table_list_cell">${book.isbn}</div>
        <div class="table_list_cell">${book.publicationDate.getYear()}</div>
    </a><br/>
</c:forEach>
</div>

</body>
</html>
