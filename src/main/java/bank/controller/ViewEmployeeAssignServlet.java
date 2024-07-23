package bank.controller;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.ViewEmployeeAssignDAO;
import bank.model.AssignedWork;

@WebServlet("/ViewEmployeeAssignServlet")
public class ViewEmployeeAssignServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ViewEmployeeAssignServlet.class.getName());

    public ViewEmployeeAssignServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ViewEmployeeAssignDAO dao = new ViewEmployeeAssignDAO();
        List<AssignedWork> assignedWorks = dao.retrieve();
        LOGGER.log(Level.INFO, "Assigned Works: {0}", assignedWorks);
        request.setAttribute("assignedWorks", assignedWorks);
        request.getRequestDispatcher("/viewAssignedWork.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
