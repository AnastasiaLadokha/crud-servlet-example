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
    <h1>Departments Management</h1>
    <h2>
        <a href="/newDepartment">Add New Department</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/listDepartment">List All Departments</a>

    </h2>
</center>
<div align="center">
    <c:if test="${department != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${department == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${department != null}">
                            Edit Department
                        </c:if>
                        <c:if test="${department == null}">
                            Add New Department
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${department != null}">
                    <input type="hidden" name="id" value="<c:out value='${department.id}' />"/>
                </c:if>
                <tr>
                    <th>Id:</th>
                    <td>
                        <input type="text" name="id" size="45"
                               value="<c:out value='${department.id}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Title:</th>
                    <td>
                        <input type="text" name="title" size="45"
                               value="<c:out value='${department.title}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Count of rooms:</th>
                    <td>
                        <input type="text" name="count_of_rooms" size="5"
                               value="<c:out value='${department.count_of_rooms}' />"
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