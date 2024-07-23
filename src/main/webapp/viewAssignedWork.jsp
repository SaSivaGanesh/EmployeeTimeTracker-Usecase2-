<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Assigned Work</title>
    <link rel="stylesheet" href="path/to/your/css/style.css"> <!-- Include your CSS file here -->
</head>
<body>
    <h1>Assigned Work</h1>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Project Name</th>
        </tr>
        <c:forEach var="work" items="${assignedWorks}">
            <tr>
                <td>${work.name}</td>
                <td>${work.projectname}</td>
            </tr>
        </c:forEach>
    </table>
    <button onclick="window.location.href='AssociateSuccessDashboard.html'">Back to Dashboard</button>
</body>
</html>
