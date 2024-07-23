package bank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.ViewProjetdao;
import bank.model.Projects;

@WebServlet("/EditProjectServlet")
public class EditProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProjectServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewProjetdao obj = new ViewProjetdao();
        try {
            List<Projects> project2 = obj.ViewProject();
            request.setAttribute("projectList", project2);
            request.getRequestDispatcher("editProjects.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
