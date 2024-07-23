/*package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bank.model.WorkStatus;

public class WorkStatusDAO {
    public List<WorkStatus> getWorkStatusByProject(String projectName) {
        List<WorkStatus> workStatusList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT employee_id, work, total_time FROM " + projectName;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String work = resultSet.getString("work");
                int totalTime = resultSet.getInt("total_time");

                WorkStatus workStatus = new WorkStatus(employeeId, work, totalTime);
                workStatusList.add(workStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(resultSet, preparedStatement, connection);
        }

        return workStatusList;
    }
} */
