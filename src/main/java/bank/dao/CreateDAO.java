package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateDAO {
    
    public boolean addEmployee(String name, String skill, String mobile, String email, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ employee_time_tracker", "root", "Ganesh");
            String sql = "INSERT INTO employee (name, skills, mobile, `e-mail`, password) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, skill);
            ps.setString(3, mobile);
            ps.setString(4, email);
            ps.setString(5, password);
            
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }
}
