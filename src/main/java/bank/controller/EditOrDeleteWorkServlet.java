package bank.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.dao.EditOrDeleteDAO;
import java.util.List;
import bank.model.Work;

@WebServlet("/EditOrDeleteWorkServlet")
public class EditOrDeleteWorkServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EditOrDeleteDAO dao = new EditOrDeleteDAO();
        List<Work> workDetailsList = dao.getAllWorks(); // Assuming you have a method to get all works
        request.setAttribute("works", workDetailsList);
        request.getRequestDispatcher("/editOrDeleteWork.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        EditOrDeleteDAO dao = new EditOrDeleteDAO();
        String message = "";

        if ("update".equals(action)) {
            String work = request.getParameter("work");
            String startTimeStr = request.getParameter("startTime");
            String endTimeStr = request.getParameter("endTime");

            Timestamp startTime = null;
            Timestamp endTime = null;

            try {
                // Assuming startTime and endTime are in the format "yyyy-MM-dd HH:mm:ss"
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startTime = new Timestamp(dateFormat.parse(startTimeStr).getTime());
                endTime = new Timestamp(dateFormat.parse(endTimeStr).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                // Handle error - for example, set an error message in the request and forward to an error page
                request.setAttribute("errorMessage", "Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            dao.updateWork(id, work, startTime, endTime);
            message = "Successfully updated.";
        } else if ("delete".equals(action)) {
            dao.deleteWork(id);
            message = "Successfully deleted.";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/operationResult.jsp").forward(request, response); 
    }
}
