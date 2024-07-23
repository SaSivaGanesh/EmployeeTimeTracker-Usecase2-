package bank.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.AddworkDAO;

@WebServlet("/AddworkServlet")
public class AddworkServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddworkServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("employee_id");
        String name = request.getParameter("employee_name");
        String work = request.getParameter("work");
        String startTimeStr = request.getParameter("start_time");
        String endTimeStr = request.getParameter("end_time");

        // Convert the datetime string to Timestamp
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Timestamp startTime = null;
        Timestamp endTime = null;
        try {
            Date startDate = format.parse(startTimeStr);
            Date endDate = format.parse(endTimeStr);
            startTime = new Timestamp(startDate.getTime());
            endTime = new Timestamp(endDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error parsing date-time values.");
            return;
        }

        AddworkDAO dao = new AddworkDAO();
        boolean isAdded = dao.addWork(id, name, work, startTime, endTime);

        response.setContentType("text/plain");
        if (isAdded) {
            response.getWriter().write("Work log added successfully.");
        } else {
            response.getWriter().write("Failed to add work log. Exceeds daily limit or duplicate entry.");
        }
        response.getWriter().println("<br><button onclick=\"window.location.href='EmployeeDashboard.html'\">Back to Dashboard</button>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
