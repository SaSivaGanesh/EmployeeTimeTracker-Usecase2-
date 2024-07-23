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
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }
        h2 {
            text-align: center;
            color: #444;
            padding: 20px 0;
            margin: 0;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        button {
            display: block;
            width: 200px;
            margin: 20px auto;
            padding: 10px;
            font-size: 16px;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #45a049;
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
            String sql = "SELECT * FROM employee ORDER BY id DESC LIMIT 1";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
    %>
    
    <table>
        <tr>
            <th>Name</th>
            <th>Skills</th>
            <th>Mobile</th>
            <th>Email</th>
        </tr>
        
        <% if(rs.next()) { %>
        <tr>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getString("skills") %></td>
            <td><%= rs.getString("mobile") %></td>
            <td><%= rs.getString("e-mail") %></td> <!-- Corrected column name -->
        </tr>
        
        
        <% } else { %>
        <tr>
            <td colspan="4">No employee found</td>
        </tr>
        <% } %>
    </table>
    
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
    <button onclick="window.location.href='AdminSuccessDashboard.html'">Back to Dashboard</button> 
</body>
</html>
