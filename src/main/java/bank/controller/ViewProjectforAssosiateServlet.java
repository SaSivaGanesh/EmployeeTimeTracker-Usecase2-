package bank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.dao.ViewProjectforAssosiateDAO;
import bank.model.Project;

@WebServlet("/ViewProjectforAssosiateServlet")
public class ViewProjectforAssosiateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewProjectforAssosiateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            ViewProjectforAssosiateDAO dao = new ViewProjectforAssosiateDAO();
            try {
            //	System.out.println(username);
                List<Project> projectList = dao.viewAssosiate(username);
                request.setAttribute("projectList", projectList);
                request.getRequestDispatcher("ViewProjectsforAssosiate.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("Login.html");
        }
    }
}
