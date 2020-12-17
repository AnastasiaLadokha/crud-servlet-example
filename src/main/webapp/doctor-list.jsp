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
    <h1>Doctors Management</h1>
    <h2>
        <a href="/newDoctor">Add New Doctor</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/listDoctor">List All Doctors</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Doctors</h2></caption>
        <tr>
            <th>ID</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Position</th>
            <th>Department id</th>
            <th>Date of birth</th>
        </tr>
        <c:forEach var="doctor" items="${doctor}">
            <tr>
                <td><c:out value="${doctor.id}" /></td>
                <td><c:out value="${doctor.first_name}" /></td>
                <td><c:out value="${doctor.last_name}" /></td>
                <td><c:out value="${doctor.position}" /></td>
                <td><c:out value="${doctor.department_id}" /></td>
                <td><c:out value="${doctor.date_of_birth}" /></td>

                <td>
                    <a href="/editDoctor?id=<c:out value='${doctor.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/deleteDoctor?id=<c:out value='${doctor.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
