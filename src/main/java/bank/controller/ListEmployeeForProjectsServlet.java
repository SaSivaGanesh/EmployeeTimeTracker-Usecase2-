package bank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.ListEmployeeForProjectsDAO;
import bank.model.ListEmployeeForProjects;

@WebServlet("/ListEmployeeForProjectsServlet")
public class ListEmployeeForProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ListEmployeeForProjectsServlet() {
        super();
      
    }

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListEmployeeForProjectsDAO obj=new ListEmployeeForProjectsDAO();
		try {
			List<ListEmployeeForProjects> list2=obj.list();
			request.setAttribute("employeeList", list2);
			request.getRequestDispatcher("ListEmployeeForProjects.jsp").forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
	}

}
