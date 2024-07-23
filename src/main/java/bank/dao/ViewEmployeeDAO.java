package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import bank.model.Employee;

public class ViewEmployeeDAO {
	public List<Employee> getEmployee() {
		List<Employee> employees =new ArrayList<Employee>();
		Connection conn =null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker", "root", "Ganesh");
			String sql ="SELECT * FROM employee";
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				String name=rs.getString("name");
				String skill=rs.getString("skills");
				String mobile = rs.getString("mobile");
				String email =rs.getString("e-mail");
				Employee employee =new Employee(name, skill, mobile, email);
				employees.add(employee);			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return employees;
		
	}

}
