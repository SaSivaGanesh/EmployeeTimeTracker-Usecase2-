package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.dao.CustomerLoginDAO;
import bank.model.PasswordUtil;


@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CustomerLoginServlet() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("username");
		String id=request.getParameter("employeeId");
		String password=request.getParameter("password");
		CustomerLoginDAO obj=new CustomerLoginDAO();
		String  pass=obj.isvaliad(name, id);
		boolean hashedpass =obj.isPasswordHashed(pass);
		System.out.println(pass);
		System.out.println(password);
		System.out.println(hashedpass);
		if(hashedpass) {
			String encryptedpass=PasswordUtil.encryptPassword(password);
			if(pass.equals(encryptedpass)) {
				System.out.println(pass);
				System.out.println(password);
				HttpSession session=request.getSession(true);
				session.setAttribute("username", name);
				session.setAttribute("employeeId", id);
				response.sendRedirect("EmployeeDashboard.html");
			}
			else {
				response.sendRedirect("Failure.html");
			}
		}else {
			if(pass.equals(password)) {
				System.out.println(pass);
				System.out.println(password);
				HttpSession session=request.getSession(true);
				session.setAttribute("username", name);
				session.setAttribute("employeeId", id);
				response.sendRedirect("EmployeeDashboard.html");
			}
			else {
				response.sendRedirect("Failure.html");
			}
			}
		}
		
	}


