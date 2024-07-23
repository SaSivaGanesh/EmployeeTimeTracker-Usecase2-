package bank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.DisplayRequestDAO;
import bank.model.Request;

@WebServlet("/DisplayRequestservlet")
public class DisplayRequestservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DisplayRequestservlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DisplayRequestDAO obj=new DisplayRequestDAO();
		List<Request> requests = null;
		try {
			requests = obj.request();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 request.setAttribute("requests", requests);
		 request.getRequestDispatcher("displayRequests.jsp").forward(request, response);
	}

}
