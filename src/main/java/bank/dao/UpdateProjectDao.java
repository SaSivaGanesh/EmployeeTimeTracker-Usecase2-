package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProjectDao {
    public boolean updateProject(String project, String description, String languages, String status, String manager_name) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker", "root", "Ganesh");

            String sql = "UPDATE projects_list SET description=?, languages=?, status=?, manager_name=? WHERE project=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, description);
            ps.setString(2, languages);
            ps.setString(3, status);
            ps.setString(4, manager_name);
            ps.setString(5, project);

            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }
}
