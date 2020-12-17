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
    <h1>Cards Management</h1>
    <h2>
        <a href="/newCard">Add New Card</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/listCard">List All Cards</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Cards</h2></caption>
        <tr>
            <th>ID</th>
            <th>Receipt date</th>
            <th>Discharge date</th>
            <th>Doctor id</th>
            <th>Patient id</th>
            <th>Disease id</th>
        </tr>
        <c:forEach var="card" items="${cards}">
            <tr>
                <td><c:out value="${card.id}" /></td>
                <td><c:out value="${card.receipt_date}" /></td>
                <td><c:out value="${card.discharge_date}" /></td>
                <td><c:out value="${card.doctor_id}" /></td>
                <td><c:out value="${card.patient_id}" /></td>
                <td><c:out value="${card.disease_id}" /></td>

                <td>
                    <a href="/editCard?id=<c:out value='${card.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/deleteCard?id=<c:out value='${card.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
