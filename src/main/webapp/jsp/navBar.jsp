<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Title</title>
</head>
<body>
<div class="nav-bar">
    <a href="index.jsp" class="nav-bar-a"> main page </a>
    <a href="/controller?command=users" methods="get" class="nav-bar-a" >users</a>
    <a href="/controller?command=books" methods="get" class="nav-bar-a" >books</a>
    <a href="/controller?command=book_create_form" methods="get" class="nav-bar-a">create book</a>
    <a href="/controller?command=user_create_form" methods="get" class="nav-bar-a">create user</a>
</div>
</body>
</html>
