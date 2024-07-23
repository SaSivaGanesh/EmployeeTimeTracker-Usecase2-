package bank.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bank.dao.*;
import bank.model.Work;

@WebServlet("/EditOrDeleteServlet")
public class EditOrDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Correct way to convert session attribute to int
        String employeeIdStr = (String) session.getAttribute("employeeId");
        int employeeId = Integer.parseInt(employeeIdStr);

        EditOrDeleteDAO dao = new EditOrDeleteDAO();
        List<Work> works = dao.getWorksByEmployeeAndProject(employeeId);

        request.setAttribute("works", works);
        request.getRequestDispatcher("EditOrDelete.jsp").forward(request, response);
    }
}
