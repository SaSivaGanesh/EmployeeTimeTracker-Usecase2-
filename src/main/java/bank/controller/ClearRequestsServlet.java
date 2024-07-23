package bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.ClearRequestsDAO;

@WebServlet("/ClearRequestsServlet")
public class ClearRequestsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClearRequestsServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClearRequestsDAO dao = new ClearRequestsDAO();
        
        boolean success = dao.clearRequests();
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Request Status</title></head>");
        response.getWriter().println("<body>");
        
        if (success) {
            response.getWriter().println("<h1>Requests cleared successfully.</h1>");
        } else {
            response.getWriter().println("<h1>Failed to clear requests.</h1>");
        }
        
        // Add the button to go back to the dashboard
        response.getWriter().println("<button onclick=\"window.location.href='AssociateSuccessDashboard.html'\">Back to Dashboard</button>");
        
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
