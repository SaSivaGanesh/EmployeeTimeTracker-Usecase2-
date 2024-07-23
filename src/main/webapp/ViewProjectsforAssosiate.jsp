<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bank.model.Project" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Projects for Associate</title>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        padding: 8px;
        text-align: left;
        border: 1px solid #ddd;
    }
    th {
        background-color: #f2f2f2;
    }
</style>
</head>
<body>
    <h2>Projects Assigned to You</h2>
    <form action="AssignEmployeeBYAssosiateServlet" method="post">
        <table>
            <thead>
                <tr>
                    <th>Project</th>
                    <th>Description</th>
                    <th>Languages</th>
                    <th>Status</th>
                    <th>Employee Name</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Project> projectList = (List<Project>) request.getAttribute("projectList");
                    if (projectList != null && !projectList.isEmpty()) {
                        for (Project project : projectList) {
                %>
                <tr>
                    <td><%= project.getProjects() %></td>
                    <td><%= project.getDescription() %></td>
                    <td><%= project.getLanguages() %></td>
                    <td><%= project.getStatus() %></td>
                    <td><input type="text" name="employeeName_<%= project.getProjects() %>" required /></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">No projects assigned to you.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <button type="submit">Assign Employee</button>
        
    </form>
     <button onclick="window.location.href='AssociateSuccessDashboard.html'">Back to Dashboard</button>
</body>
</html>
