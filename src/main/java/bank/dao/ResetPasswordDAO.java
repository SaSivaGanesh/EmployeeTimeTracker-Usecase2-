package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResetPasswordDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_time_tracker";
    private static final String USER = "root";
    private static final String PASS = "Ganesh";
    

    public String getStoredPassword(int employeeId) {
        String query = "SELECT password FROM employee WHERE id = ?";
        String pass="null";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pass= rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pass;
    }

    public boolean isPasswordHashed(String password) {
        // Example criteria for hashed password (e.g., SHA-256)
        return password != null && password.length() == 64; // Length of SHA-256 hash in hex
    }


    public boolean  updatePassword(int employeeId, String encryptedPassword) {
        String query = "UPDATE employee SET password = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, encryptedPassword);
            ps.setInt(2, employeeId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
