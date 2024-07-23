package bank.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import bank.dao.LoginDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginDAO obj = new LoginDAO();
        String role = obj.isValidate(username, password);
        if (role != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            
            if (role.equals("admin")) {
                response.sendRedirect("AdminSuccessDashboard.html");
            } else if (role.equals("Associate")) {
                response.sendRedirect("AssociateSuccessDashboard.html");
            } else {
                response.sendRedirect("Failure.html");
            }
        } else {
            response.sendRedirect("Failure.html");
        }
    }
}
