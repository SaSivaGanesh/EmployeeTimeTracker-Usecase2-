package bank.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bank.model.AssignedWork;

public class ViewEmployeeAssignDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_time_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Ganesh";
    private static final Logger LOGGER = Logger.getLogger(ViewEmployeeAssignDAO.class.getName());

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    public List<AssignedWork> retrieve() {
        List<AssignedWork> assignedWorks = new ArrayList<>();
        String sql = "SELECT name, projectname FROM projectsforemployee";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                String projectname = rs.getString("projectname");
                AssignedWork work = new AssignedWork(name, projectname);
                assignedWorks.add(work);
            }
            LOGGER.log(Level.INFO, "Data retrieved: {0}", assignedWorks);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Exception: ", e);
        }

        return assignedWorks;
    }
}
