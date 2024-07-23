package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.dao.ProcessRequestDAO;

@WebServlet("/ProcessRequestServlet")
public class ProcessRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the form
        String project = request.getParameter("projectName");
        String name = request.getParameter("employeeName");
        String id = request.getParameter("employeeId");
        String skill = request.getParameter("skill");

        // Retrieve username from session
        HttpSession session = request.getSession(false); // Do not create a new session if none exists
        if (session != null) {
            String sessionUsername = (String) session.getAttribute("username");
            System.out.println("Session Username: " + sessionUsername); // Debugging line

            // Check if session username matches the entered username
            if (sessionUsername != null && sessionUsername.equals(name)) {
                // Username matches, process the request
                ProcessRequestDAO obj = new ProcessRequestDAO();
                boolean update = obj.request(project, name, id, skill);
                if (update) {
                    response.sendRedirect("Requestsent.html");
                } else {
                    response.sendRedirect("error.jsp");
                }
            } else {
                // Redirect to an error page or handle unauthorized access
                response.sendRedirect("error.jsp");
            }
        } else {
            // Redirect to login page or handle session timeout
            response.sendRedirect("Login.html");
        }
    }
}
