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
    <h1>Cards Management</h1>
    <h2>
        <a href="/newCard">Add New Card</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/listCard">List All Cards</a>

    </h2>
</center>
<div align="center">
    <c:if test="${card != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${card == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${card != null}">
                            Edit Card
                        </c:if>
                        <c:if test="${card == null}">
                            Add New Card
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${card != null}">
                    <input type="hidden" name="id" value="<c:out value='${card.id}' />"/>
                </c:if>
                <tr>
                    <th>Id:</th>
                    <td>
                        <input type="text" name="id" size="45"
                               value="<c:out value='${card.id}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Receipt date:</th>
                    <td>
                        <input type="date" name="name" size="45"
                               value="<c:out value='${card.receipt_date}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Discharge date:</th>
                    <td>
                        <input type="date" name="discharge_date" size="45"
                               value="<c:out value='${card.discharge_date}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Doctor id:</th>
                    <td>
                        <input type="text" name="doctor_id" size="45"
                               value="<c:out value='${card.doctor_id}' />"
                        />
                    </td>
                </tr>
                <tr>
                <tr>
                    <th>Patient id:</th>
                    <td>
                        <input type="text" name="patient_id" size="45"
                               value="<c:out value='${card.patient_id}' />"
                        />
                    </td>
                </tr>
                <tr>
                <tr>
                    <th>Disease id:</th>
                    <td>
                        <input type="text" name="disease_id" size="45"
                               value="<c:out value='${card.disease_id}' />"
                        />
                    </td>
                </tr>
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