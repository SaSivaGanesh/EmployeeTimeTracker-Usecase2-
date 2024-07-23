<%@ page import="java.util.List" %>
<%@ page import="bank.model.Request" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            padding: 20px;
            margin: 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
            color: #333;
        }
        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tbody tr:hover {
            background-color: #f1f1f1;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        button {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Requests</h1>
    <table>
        <thead>
            <tr>
                <th>Project</th>
                <th>Employee Name</th>
                <th>Employee ID</th>
                <th>Skills</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Request> requests = (List<Request>) request.getAttribute("requests");
                if (requests != null && !requests.isEmpty()) {
                    for (Request req : requests) { 
            %>
                <tr>
                    <td><%= req.getProject() %></td>
                    <td><%= req.getName() %></td>
                    <td><%= req.getId() %></td>
                    <td><%= req.getSkill() %></td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr>
                    <td colspan="4" style="text-align: center;">No requests found.</td>
                </tr>
            <% 
                }
            %>
        </tbody>
    </table>
    <button onclick="window.location.href='AssociateSuccessDashboard.html'">Back to Dashboard</button>
</body>
</html>
