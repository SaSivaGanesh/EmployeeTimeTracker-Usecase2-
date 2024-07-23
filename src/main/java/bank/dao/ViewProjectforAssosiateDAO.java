package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.model.Project;

public class ViewProjectforAssosiateDAO {
	public List<Project> viewAssosiate(String manager_name) throws SQLException {
		List<Project> p=new ArrayList<Project>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker","root","Ganesh");
			String sql="SELECT * from projects_list where manager_name=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, manager_name);
			rs=ps.executeQuery();
			while(rs.next()) {
				String project=rs.getString("project");
				String description=rs.getString("description");
				String languages=rs.getString("languages");
				String status=rs.getString("status");
				Project obj1=new Project(project, description, languages, status);
				p.add(obj1);
			}
			
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}
		
		return p;
		
	}

}
