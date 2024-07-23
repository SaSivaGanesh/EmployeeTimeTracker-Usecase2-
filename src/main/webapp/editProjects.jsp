<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bank.model.Projects" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Projects</title>
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
    <h2>Edit Projects</h2>
    <form action="UpdateProjectServlet" method="post">
        <table>
            <thead>
                <tr>
                    <th>Project</th>
                    <th>Description</th>
                    <th>Languages</th>
                    <th>Status</th>
                    <th>Manager Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Projects> projectList = (List<Projects>) request.getAttribute("projectList");
                    for (int i = 0; i < projectList.size(); i++) {
                        Projects project = projectList.get(i);
                %>
                <tr>
                    <td><input type="text" name="projects" value="<%= project.getProjects() %>" readonly /></td>
                    <td><input type="text" name="descriptions" value="<%= project.getDescription() %>" /></td>
                    <td><input type="text" name="languages" value="<%= project.getLanguages() %>" /></td>
                    <td><input type="text" name="status" value="<%= project.getStatus() %>" /></td>
                    <td><input type="text" name="manager_names" value="<%= project.getManager_name() %>" /></td>
                    <td><button type="submit">Update</button></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <button onclick="window.location.href='AdminSuccessDashboard.html'">Back to Dashboard</button>
    </form>
</body>
</html>
