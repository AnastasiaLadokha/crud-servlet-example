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
    <h1>Diseases Management</h1>
    <h2>
        <a href="/newDisease">Add New Disease</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/listDisease">List All Diseases</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Diseases</h2></caption>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Treatment</th>
            <th>Prevention</th>
        </tr>
        <c:forEach var="disease" items="${diseases}">
            <tr>
                <td><c:out value="${disease.id}" /></td>
                <td><c:out value="${disease.title}" /></td>
                <td><c:out value="${disease.treatment}" /></td>
                <td><c:out value="${disease.prevention}" /></td>
                <td>
                    <a href="/editDisease?id=<c:out value='${disease.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/deleteDisease?id=<c:out value='${disease.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
