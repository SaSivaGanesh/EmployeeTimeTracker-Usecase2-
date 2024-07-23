package bank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.ViewEmployeeDAO;
import bank.dao.ViewProjetdao;
import bank.model.Employee;
import bank.model.Projects;

@WebServlet("/ViewProjectByEmployeeServlet")
public class ViewProjectByEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ViewProjectByEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ViewProjetdao obj=new ViewProjetdao();
		try {
			List<Projects> project2=obj.ViewProject();
			request.setAttribute("projectList", project2);
			request.getRequestDispatcher("viewProjects.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

}
