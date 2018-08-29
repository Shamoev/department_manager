<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee</title>
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
<form action="addmodempl" method="post">
    <%--<input type="hidden" name="emplId" value="${employee.emplId}">--%>
    <table>
        <caption><h1>Employee</h1></caption>
        <tr>
            <td>Surname</td>
            <td><input type="text" name="surname" value="${employee.surname}"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${employee.email}"></td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td><input type="text" name="dateOfBirth" value="${employee.dateOfBirth}"></td>
        </tr>
        <tr>
            <td>Salary</td>
            <td><input type="text" name="salary" value="${employee.salary}"></td>
        </tr>
        <tr>
            <td><button type="submit">Confirm</button></td>
            <td><button formaction="/depmanager/employees">List of employees</button></td>

        </tr>
    </table>
</form>
<h4 align="center"><i>${message}</i></h4>
</body>
</html>
