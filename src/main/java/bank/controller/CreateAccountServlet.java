package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.CreateDAO;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public CreateAccountServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String skills = request.getParameter("skill"); // Corrected parameter name
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        CreateDAO dao = new CreateDAO();
        boolean success = dao.addEmployee(name, skills, mobile, email, password); // Corrected method name
        
        if(success) {
            request.getRequestDispatcher("AddEmployeeSuccess.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
