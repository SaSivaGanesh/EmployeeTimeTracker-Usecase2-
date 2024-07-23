package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	public String isValidate(String name, String pass) {
		String role =null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker", "root", "Ganesh");
			String sql="SELECT role FROM  admin_table  WHERE username=? and password=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
			rs=ps.executeQuery();
			if (rs.next()) {
				role =rs.getString("role");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (ps !=null) ps.close();
				if (conn !=null) conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		return role;
	}

    
}
