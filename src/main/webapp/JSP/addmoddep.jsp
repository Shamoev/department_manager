<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department</title>
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
<form action="addmoddep" method="post">
   <%-- <input type="hidden" name="depId" value="${department.depId}">--%>
    <table>
        <caption><h1>Department</h1></caption>
        <tr>
            <td>Department Number</td>
            <td><input type="text" name="depNumber" value="${department.depNumber}"></td>
        </tr>
        <tr>
            <td>Department Name</td>
            <td><input type="text" name="depName" value="${department.depName}"></td>
        </tr>
        <tr>
            <td><button type="submit">Confirm</button></td>
            <td><button formaction="/depmanger/departments">List of departments</button></td>
        </tr>
    </table>
</form>
<h4 align="center"><i>${message}</i></h4>
</body>
</html>