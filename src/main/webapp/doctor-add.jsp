<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <c:if test="${doctor != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${doctor == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${doctor != null}">
                            Edit Doctor
                        </c:if>
                        <c:if test="${doctor == null}">
                            Add New Doctor
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${doctor != null}">
                    <input type="hidden" name="id" value="<c:out value='${doctor.id}' />"/>
                </c:if>
                <tr>
                    <th>Id:</th>
                    <td>
                        <input type="text" name="id" size="45"
                               value="<c:out value='${doctor.id}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>First name:</th>
                    <td>
                        <input type="text" name="first_name" size="45"
                               value="<c:out value='${doctor.first_name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Last name:</th>
                    <td>
                        <input type="text" name="last_name" size="45"
                               value="<c:out value='${doctor.last_name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Position:</th>
                    <td>
                        <input type="text" name="position" size="45"
                               value="<c:out value='${doctor.position}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Department id:</th>
                    <td>
                        <input type="text" name="department_id" size="45"
                               value="<c:out value='${doctor.department_id}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Date of birth:</th>
                    <td>
                        <input type="date" name="date_of_birth" size="45"
                               value="<c:out value='${doctor.date_of_birth}' />"
                        />
                    </td>
                </tr>
                <tr>

                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>