package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.model.Projects;
import bank.model.Request;

public class DisplayRequestDAO {
	public List<Request> request() throws SQLException{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Request> request=new ArrayList<Request>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker","root","Ganesh");
			String sql="SELECT * FROM requests";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				String project =rs.getString("project");
				String employee_name=rs.getString("employee_name");
				String  employee_id=rs.getString("employee_id");
				String  skills =rs.getString("skills");
				
				Request obj1 =new Request(project, employee_name,  employee_id,skills);
				request.add(obj1);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		}
		return request;
		
	}

}
