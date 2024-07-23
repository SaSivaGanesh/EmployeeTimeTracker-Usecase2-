package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.model.ListEmployeeForProjects;
import bank.model.Project;

public class ListEmployeeForProjectsDAO {
	public List<ListEmployeeForProjects> list() throws SQLException{
		List<ListEmployeeForProjects> list=new ArrayList<ListEmployeeForProjects>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker","root","Ganesh");
			String sql="SELECT * from projectsforemployee";
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String projectname=rs.getString("projectname");
				
				ListEmployeeForProjects list1=new ListEmployeeForProjects(id, name,projectname);
				list.add(list1);
			}
			
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}
		return list;
		
		
	}
		
		
	

}
