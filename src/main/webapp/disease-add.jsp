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
    <h1>Diseases Management</h1>
    <h2>
        <a href="/newDisease">Add New Disease</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/listDisease">List All Diseases</a>

    </h2>
</center>
<div align="center">
    <c:if test="${disease != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${disease == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${disease != null}">
                            Edit Disease
                        </c:if>
                        <c:if test="${disease == null}">
                            Add New Disease
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${disease != null}">
                    <input type="hidden" name="id" value="<c:out value='${disease.id}' />"/>
                </c:if>
                <tr>
                    <th>Id:</th>
                    <td>
                        <input type="text" name="id" size="45"
                               value="<c:out value='${disease.id}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Title:</th>
                    <td>
                        <input type="text" name="title" size="45"
                               value="<c:out value='${disease.title}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Treatment:</th>
                    <td>
                        <input type="text" name="treatment" size="45"
                               value="<c:out value='${disease.treatment}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Prevention:</th>
                    <td>
                        <input type="text" name="prevention" size="45"
                               value="<c:out value='${disease.prevention}' />"
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