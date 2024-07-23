package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.Driver;

public class CustomerLoginDAO {
	public String isvaliad(String name, String id) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String pass="null";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker","root","Ganesh");
			String sql="SELECT password FROM employee WHERE id=? and name=? ";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				pass=rs.getString("password");
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
		return pass;
		
	}
	public boolean isPasswordHashed(String password) {
        
        return password != null && password.length() == 64; // Length of SHA-256 hash in hex
    }

}
