<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Employees</title>
    <style>
        table {
            width: 800px;
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
<form action="employees" method="post">
    <table>
        <caption><h1>List of Employees in "${department.depName}" department</h1></caption>
        <tr>
            <th>Edit / Delete</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Date of Birth</th>
            <th>Salary</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>
                    <input name="emplIdRadio" type="radio" value="${employee.emplId}">
                </td>
                <td>${employee.surname}</td>
                <td>${employee.email}</td>
                <td>${employee.dateOfBirth}</td>
                <td>${employee.salary}</td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <table>
        <tr>
            <td><button name="action" value="add">Add</button></td>
            <td><button name="action" value="modify">Modify</button></td>
            <td><button name="action" value="remove">Remove</button></td>
            <td><button formaction="/depmanager/departments">List of departments</button></td>
        </tr>
    </table>
    <h4 align="center"><i>${message}</i></h4>
</form>
</body>
</html>
