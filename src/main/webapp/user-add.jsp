<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
<center>
    <h1>Books Management</h1>
    <h2>
        <a href="/new">Add New Human</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Humans</a>

    </h2>
</center>
<div align="center">
    <c:if test="${human != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${human == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${human != null}">
                            Edit HUman
                        </c:if>
                        <c:if test="${human == null}">
                            Add New Human
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${human != null}">
                    <input type="hidden" name="id" value="<c:out value='${human.id}' />"/>
                </c:if>
                <tr>
                    <th>Id:</th>
                    <td>
                        <input type="text" name="id" size="45"
                               value="<c:out value='${human.id}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Name:</th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${human.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Surname:</th>
                    <td>
                        <input type="text" name="surname" size="5"
                               value="<c:out value='${human.surname}' />"
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