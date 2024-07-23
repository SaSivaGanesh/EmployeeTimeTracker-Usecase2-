package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class AddworkDAO {

    public boolean addWork(String id, String name, String work, Timestamp startTime, Timestamp endTime) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_time_tracker", "root", "Ganesh");

            // Check total daily working hours
            String checkSql = "SELECT SUM(TIMESTAMPDIFF(MINUTE, start_time, end_time)) AS total_minutes "
                            + "FROM banking_system "
                            + "WHERE employee_id = ? AND DATE(start_time) = DATE(?)";
            ps = conn.prepareStatement(checkSql);
            ps.setString(1, id);
            ps.setTimestamp(2, startTime);
            rs = ps.executeQuery();
            int totalMinutes = 0;
            if (rs.next()) {
                totalMinutes = rs.getInt("total_minutes");
            }
            int newEntryMinutes = (int) ((endTime.getTime() - startTime.getTime()) / 60000);
            totalMinutes += newEntryMinutes;

            // Check if total daily working hours exceed 8 hours
            if (totalMinutes > 480) { // 8 hours * 60 minutes
                return false; // Exceeds daily limit
            }

            // Calculate total hours and minutes
            int totalHours = totalMinutes / 60;
            int minutes = totalMinutes % 60;
            String totalHoursStr = String.format("%dH %dM", totalHours, minutes); // Ensure shorter format

            // Insert new work log
            String sql = "INSERT INTO banking_system (employee_id, employee_name, work, start_time, end_time, total_time) VALUES (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, work);
            ps.setTimestamp(4, startTime);
            ps.setTimestamp(5, endTime);
            ps.setString(6, totalHoursStr); // Insert total_time
            int rowInserted = ps.executeUpdate();
            if (rowInserted > 0) {
                updated = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return updated;
    }
}
