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
    <h1>Patients Management</h1>
    <h2>
        <a href="/newPatient">Add New Patient</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/listPatient">List All Patients</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Patients</h2></caption>
        <tr>
            <th>ID</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Address</th>
            <th>Date of birth</th>
            <th>Status</th>
        </tr>
        <c:forEach var="doctor" items="${patient}">
            <tr>
                <td><c:out value="${patient.id}" /></td>
                <td><c:out value="${patient.first_name}" /></td>
                <td><c:out value="${patient.last_name}" /></td>
                <td><c:out value="${patient.address}" /></td>
                <td><c:out value="${patient.date_of_birth}" /></td>
                <td><c:out value="${patient.status}" /></td>

                <td>
                    <a href="/editPatient?id=<c:out value='${patient.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/deletePatient?id=<c:out value='${patient.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
