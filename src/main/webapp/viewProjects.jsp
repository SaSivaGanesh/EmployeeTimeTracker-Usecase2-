<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Projects</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Project List</h2>
    <c:if test="${not empty projectList}">
        <table>
            <thead>
                <tr>
                    <th>Project Name</th>
                    <th>Description</th>
                    <th>Languages</th>
                    <th>Status</th>
                    <th>Manager Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="project" items="${projectList}">
                    <tr>
                        <td>${project.projects}</td>
                        <td>${project.description}</td>
                        <td>${project.languages}</td>
                        <td>${project.status}</td>
                        <td>${project.manager_name}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty projectList}">
        <p>No projects found.</p>
    </c:if>
    
    <button onclick="window.location.href='EmployeeDashboard.html'">Back to Dashboard</button>
</body>
</html>
