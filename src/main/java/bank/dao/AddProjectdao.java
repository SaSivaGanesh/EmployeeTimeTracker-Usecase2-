package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddProjectdao {
	public boolean addproject(String Project, String description, String languages, String Status, String Assosiate) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean updated=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker","root","Ganesh");
			String sql="Insert into projects_list (project, description, languages, status, manager_name) VALUES(?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, Project);
			ps.setString(2, description);
			ps.setString(3, languages);
			ps.setString(4, Status);
			ps.setString(4, Assosiate);
			int rowinserted =ps.executeUpdate();
			if (rowinserted>0) {
				updated=true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return updated;
		
	}

}
