<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            padding: 20px;
            margin: 0;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        h2 {
            text-align: center;
            color: #333;
        }
    </style>
</head>
<body>
    <h2>Employee List</h2>
    
    <table>
        <tr>
            <th>Name</th>
            <th>Skills</th>
            <th>Mobile</th>
            <th>Email</th>
        </tr>
        
        <%@ page import="bank.model.Employee" %>
        <%@ page import="java.util.List" %>
        <% List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>
        <% if (employees != null && !employees.isEmpty()) { %>
            <% for (Employee employee : employees) { %>
            <tr>
                <td><%= employee.getName() %></td>
                <td><%= employee.getSkill() %></td>
                <td><%= employee.getMobile() %></td>
                <td><%= employee.getEmail() %></td>
            </tr>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="4" style="text-align: center;">No employees found</td>
            </tr>
        <% } %>
    </table>
   
</body>
</html>
