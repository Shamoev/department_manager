<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Departments</title>
    <style>
        table {
            width: 400px;
            margin: auto;
            border-collapse: collapse;
        }
        td, th {
            text-align: center;
            border: 1px solid green;
            padding: 10px;
        }
    </style>
</head>

<body>
<form action="departments" method="post">
    <table>
        <caption><h1>List of departments</h1></caption>
        <tr>
            <th>Edit / Delete</th>
            <th>DepNumber</th>
            <th>DepName</th>
        </tr>
        <c:forEach var="department" items="${departments}">
            <tr>
                <td>
                    <input name="depIdRadio" type="radio" value="${department.depId}">
                </td>
                <td>${department.depNumber}</td>
                <td>${department.depName}</td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <table>
        <tr>
            <td><button name="action" value="add">Add</button></td>
            <td><button name="action" value="modify">Modify</button></td>
            <td><button name="action" value="remove">Remove</button></td>
            <td><button name="action" value="list">List of Employees</button></td>
        </tr>
    </table>
    <h4 align="center"><i>${message}</i></h4>
</form>
</body>
</html>
