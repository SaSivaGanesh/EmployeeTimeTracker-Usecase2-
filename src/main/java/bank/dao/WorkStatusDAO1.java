package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bank.model.WorkStatus;

public class WorkStatusDAO1 {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_time_tracker";
    private static final String USER = "root";
    private static final String PASS = "Ganesh";

    public List<WorkStatus> getWorkStatusByProject(String projectName) {
        List<WorkStatus> workStatusList = new ArrayList<>();
        String query = "SELECT employee_id, employee_name, work, total_time, start_time, end_time FROM " + projectName;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                WorkStatus workStatus = new WorkStatus(
                        rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("work"),
                        rs.getString("total_time"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time")
                );
                workStatusList.add(workStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workStatusList;
    }
}
