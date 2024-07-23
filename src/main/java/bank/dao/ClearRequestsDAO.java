package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ClearRequestsDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_time_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Ganesh";

    public boolean clearRequests() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "DELETE FROM requests";
            ps = conn.prepareStatement(sql);

            // Execute the update
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
          
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
