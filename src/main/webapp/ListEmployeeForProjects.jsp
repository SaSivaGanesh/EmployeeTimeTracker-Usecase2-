<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bank.model.ListEmployeeForProjects" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Employees for Projects</title>
    <style>
        /* Add your CSS styles here */
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
            margin-top: 20px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>List of Employees for Projects</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Project Name</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<ListEmployeeForProjects> employeeList = (List<ListEmployeeForProjects>) request.getAttribute("employeeList");
                if (employeeList != null && !employeeList.isEmpty()) {
                    for (ListEmployeeForProjects employee : employeeList) {
            %>
            <tr>
                <td><%= employee.getId() %></td>
                <td><%= employee.getName() %></td>
                <td><%= employee.getProjectname() %></td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr>
                <td colspan="3">No employees for projects found.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <button onclick="window.location.href='EmployeeDashboard.html'">Back to Dashboard</button>
</body>
</html>
