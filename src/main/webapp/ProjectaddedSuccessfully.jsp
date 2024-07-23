<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Latest Employee Created</h2>
    
    <%@ page import="java.sql.*" %>
    <% 
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker", "root", "Ganesh");
            String sql = "SELECT * FROM projects_list ORDER BY id DESC LIMIT 1";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
    %>
    
    <table>
        <tr>
            <th>Project</th>
            <th>Description</th>
            <th>Languages</th>
            <th>Status</th>
            <th>Assosiate</th>
           
        </tr>
        
        <% if(rs.next()) { %>
        <tr>
            <td><%= rs.getString("project") %></td>
            <td><%= rs.getString("description") %></td>
            <td><%= rs.getString("languages") %></td>
            <td><%= rs.getString("status") %></td>
            <td><%= rs.getString("manager_name") %></td>
            
        </tr>
        <% } else { %>
        <tr>
            <td colspan="4">No employee found</td>
        </tr>
        <% } %>
    </table>
    <button onclick="window.location.href='AdminSuccessDashboard.html'">Back to Dashboard</button>
    
    <% 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    %>
    
</body>
</html>
