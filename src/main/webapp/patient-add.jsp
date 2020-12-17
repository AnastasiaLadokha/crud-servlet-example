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
    <h1>Patients Management</h1>
    <h2>
        <a href="/newPatient">Add New Patient</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/listPatient">List All Patients</a>

    </h2>
</center>
<div align="center">
    <c:if test="${patient != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${patient == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${patient != null}">
                            Edit Patient
                        </c:if>
                        <c:if test="${patient == null}">
                            Add New Patient
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${patient != null}">
                    <input type="hidden" name="id" value="<c:out value='${patient.id}' />"/>
                </c:if>
                <tr>
                    <th>Id:</th>
                    <td>
                        <input type="text" name="id" size="45"
                               value="<c:out value='${patient.id}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>First name:</th>
                    <td>
                        <input type="text" name="first_name" size="45"
                               value="<c:out value='${patient.first_name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Last name:</th>
                    <td>
                        <input type="text" name="last_name" size="45"
                               value="<c:out value='${patient.last_name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td>
                        <input type="text" name="address" size="45"
                               value="<c:out value='${patient.address}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Phone number:</th>
                    <td>
                        <input type="text" name="phone_number" size="45"
                               value="<c:out value='${patient.phone_number}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Date of birth:</th>
                    <td>
                        <input type="date" name="date_of_birth" size="45"
                               value="<c:out value='${patient.date_of_birth}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Status:</th>
                    <td>
                        <input type="text" name="status" size="45"
                               value="<c:out value='${patient.status}' />"
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