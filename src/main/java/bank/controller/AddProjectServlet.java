package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.AddProjectdao;

@WebServlet("/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddProjectServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String project=request.getParameter("projectName");
		String description=request.getParameter("projectDescription");
		String languages=request.getParameter("programmingLanguages");
		String Status=request.getParameter("Status");
		String Assosiate=request.getParameter("Assosiate");
		AddProjectdao obj=new AddProjectdao();
		boolean project_updated=obj.addproject(project, description, languages,Status,Assosiate);
		if(project_updated) {
			request.getRequestDispatcher("ProjectaddedSuccessfully.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("error.jsp");
		}
	}

}
