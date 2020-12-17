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

    <title>Hospital Application</title>
</head>
<body>
<center>
    <jsp:include page="index.jsp"></jsp:include>
    <h1>Departments Management</h1>
    <h2>
        <a href="/newDepartment">Add New Department</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/listDepartment">List All Departments</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Departments</h2></caption>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Count of rooms</th>
        </tr>
        <c:forEach var="department" items="${departments}">
            <tr>
                <td><c:out value="${department.id}" /></td>
                <td><c:out value="${department.title}" /></td>
                <td><c:out value="${department.count_of_rooms}" /></td>

                <td>
                    <a href="/editDepartment?id=<c:out value='${department.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/deleteDepartment?id=<c:out value='${department.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
