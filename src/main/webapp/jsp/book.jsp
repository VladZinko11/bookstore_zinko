<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 14:03
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
    <div class="book">
        <div class="book_name"><c:out value="${book.author}"></c:out></div>
        <div class="book_name"><c:out value="${book.title}"></c:out></div>
        <div class="book_isbn "><c:out value="${book.isbn}"></c:out></div>
        <div class="book_date"><c:out value="${book.publicationDate.getYear()}"></c:out></div>
    </div>
</body>
</html>
