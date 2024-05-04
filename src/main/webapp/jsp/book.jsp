<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>
    <c:out value="${book.id}"></c:out>
    <c:out value="${book.author}"></c:out>
    <c:out value="${book.title}"></c:out>
    <c:out value="${book.isbn}"></c:out>
    <c:out value="${book.publicationDate.getYear()}"></c:out>
</h1>
</body>
</html>
