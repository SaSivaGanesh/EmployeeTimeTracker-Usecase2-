package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.model.Projects;
public class ViewProjetdao {
	public List<Projects> ViewProject() throws SQLException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Projects> project=new ArrayList<Projects>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker","root","Ganesh");
			String sql="SELECT * FROM projects_list";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				String project1 =rs.getString("project");
				String description=rs.getString("description");
				String languages=rs.getString("languages");
				String  status =rs.getString("status");
				String  manager_name=rs.getString("manager_name");
				Projects obj1=new Projects(project1, description,languages,status,manager_name);
				project.add(obj1);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		}
		return project;
	}

}
