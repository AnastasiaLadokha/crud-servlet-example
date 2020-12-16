<%--
  Created by IntelliJ IDEA.
  User: dimas
  Date: 22.11.20
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>

<html>
<head>

    <title>Human CRUD Application</title>
</head>
<body>
<center>
    <h1>Human Management</h1>
    <h2>
        <a href="/new">Add New Human</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Humans</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Humans</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Sex</th>
        </tr>
        <c:forEach var="human" items="${humans}">
            <tr>
                <td><c:out value="${human.id}" /></td>
                <td><c:out value="${human.name}" /></td>
                <td><c:out value="${human.sex}" /></td>
                <td>
                    <a href="/edit?id=<c:out value='${human.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=<c:out value='${human.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
