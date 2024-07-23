package bank.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.dao.UpdateProjectDao;

@WebServlet("/UpdateProjectServlet")
public class UpdateProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] projects = request.getParameterValues("projects");
        String[] descriptions = request.getParameterValues("descriptions");
        String[] languages = request.getParameterValues("languages");
        String[] status = request.getParameterValues("status");
        String[] manager_names = request.getParameterValues("manager_names");

        UpdateProjectDao dao = new UpdateProjectDao();
        boolean isAllUpdated = true;
        try {
            for (int i = 0; i < projects.length; i++) {
                boolean isUpdated = dao.updateProject(projects[i], descriptions[i], languages[i], status[i], manager_names[i]);
                if (!isUpdated) {
                    isAllUpdated = false;
                    break;
                }
            }
            if (isAllUpdated) {
                response.sendRedirect("EditedSuccess.html"); 
            } else {
                response.sendRedirect("error.jsp"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); 
        }
    }
}
