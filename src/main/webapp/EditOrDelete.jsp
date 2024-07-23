<%@ page import="java.util.List" %>
<%@ page import="bank.model.Work" %>
<%@ page import="bank.dao.EditOrDeleteDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit or Delete Work</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        h2 {
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
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
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #45a049;
        }

        input[type="text"] {
            width: calc(100% - 20px);
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .actions {
            display: flex;
            justify-content: space-between;
        }

        .form-inline {
            display: flex;
            gap: 10px;
        }

        .form-inline input {
            margin: 5px 0;
            flex: 1;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit or Delete Work</h2>
        <table>
            <thead>
                <tr>
                    <th>Work ID</th>
                    <th>Work Description</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Work> workDetailsList = (List<Work>) request.getAttribute("works");
                    if (workDetailsList != null && !workDetailsList.isEmpty()) {
                        for (Work work : workDetailsList) {
                %>
                    <tr>
                        <td><%= work.getId() %></td>
                        <td>
                            <form action="EditOrDeleteWorkServlet" method="post" style="display: flex; gap: 10px;">
                                <input type="hidden" name="id" value="<%= work.getId() %>">
                                <input type="hidden" name="action" value="update">
                                <input type="text" name="work" value="<%= work.getWork() %>" required>
                                <input type="text" name="startTime" value="<%= work.getStartTime() %>" required>
                                <input type="text" name="endTime" value="<%= work.getEndTime() %>" required>
                                <button type="submit">Update</button>
                            </form>
                            <form action="EditOrDeleteWorkServlet" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="<%= work.getId() %>">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">No work details found for the specified project.</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
    <button onclick="window.location.href='EmployeeDashboard.html'">Back to Dashboard</button>
</body>
</html>
