package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProcessRequestDAO {
	public boolean request(String project, String emplyee, String id, String skill) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean update=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker","root","Ganesh");
			String sql="INSERT INTO  requests (project, employee_name,  employee_id,  skills) VALUES (?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, project);
			ps.setString(2, emplyee);
			ps.setString(3, id);
			ps.setString(4, skill);
			int Rowinserted =ps.executeUpdate();
			if(Rowinserted>0) {
				update=true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return update;
		
	}

	}


