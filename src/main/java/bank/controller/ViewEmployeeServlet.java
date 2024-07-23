package bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.ViewEmployeeDAO;
import bank.model.Employee;


@WebServlet("/ViewEmployeeServlet")
public class ViewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewEmployeeServlet() {
        super();
     
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ViewEmployeeDAO obj = new ViewEmployeeDAO();
		List<Employee> employees=obj.getEmployee();
		request.setAttribute("employees", employees);
		request.getRequestDispatcher("displayEmployees.jsp").forward(request, response);
	}

}
