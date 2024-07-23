package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import bank.model.Work;

public class EditOrDeleteDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_time_tracker";
    private static final String USER = "root";
    private static final String PASS = "Ganesh";

    // Retrieve works by employee and project
    public List<Work> getWorksByEmployeeAndProject(int employeeId) {
        String query = "SELECT * FROM banking_system WHERE employee_id = ?";
        List<Work> works = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String work = rs.getString("work");
                Timestamp startTime = rs.getTimestamp("start_time");
                Timestamp endTime = rs.getTimestamp("end_time");
                String totalTime = rs.getString("total_time");

                // Convert Timestamp to String
                String startTimeStr = startTime != null ? startTime.toString() : null;
                String endTimeStr = endTime != null ? endTime.toString() : null;

                works.add(new Work(id, employeeId, work, startTimeStr, endTimeStr, totalTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return works;
    }

    // Retrieve all works
    public List<Work> getAllWorks() {
        String query = "SELECT * FROM banking_system";
        List<Work> works = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int employeeId = rs.getInt("employee_id");
                String work = rs.getString("work");
                Timestamp startTime = rs.getTimestamp("start_time");
                Timestamp endTime = rs.getTimestamp("end_time");
                String totalTime = rs.getString("total_time");

                // Convert Timestamp to String
                String startTimeStr = startTime != null ? startTime.toString() : null;
                String endTimeStr = endTime != null ? endTime.toString() : null;

                works.add(new Work(id, employeeId, work, startTimeStr, endTimeStr, totalTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return works;
    }

    // Update work
    public void updateWork(int id, String newWork, Timestamp newStartTime, Timestamp newEndTime) {
        String query = "UPDATE banking_system SET work = ?, start_time = ?, end_time = ?, total_time = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Calculate the total time in minutes
            long startTimeMillis = newStartTime.getTime();
            long endTimeMillis = newEndTime.getTime();
            long durationMillis = endTimeMillis - startTimeMillis;
            int totalMinutes = (int) (durationMillis / 60000);

            // Convert minutes to hours and minutes
            int hours = totalMinutes / 60;
            int minutes = totalMinutes % 60;
            String totalTimeStr = String.format("%dH %dM", hours, minutes);

            // Set parameters for the SQL query
            ps.setString(1, newWork);
            ps.setTimestamp(2, newStartTime);
            ps.setTimestamp(3, newEndTime);
            ps.setString(4, totalTimeStr);
            ps.setInt(5, id);

            // Execute the update
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete work
    public void deleteWork(int id) {
        String query = "DELETE FROM banking_system WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
