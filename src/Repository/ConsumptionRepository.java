package Repository;

import Domain.*;
import config.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionRepository {

    public boolean add(Consumption consumption, int user_id) {
        String query = "INSERT INTO consumptions (user_id , carbon_quantity , impact , consumption_type ,consumption_impact_type , start_date , end_date) VALUES(?,?,?,?,?,?,?)";
        try (Connection connection = Database.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setFloat(2, consumption.getCarbonQuantity());
            preparedStatement.setFloat(3, consumption.getImpact());
            preparedStatement.setString(4, consumption.getConsumptionType().toString());
            preparedStatement.setString(5, consumption.getConsumptionImpactType().toString());
            preparedStatement.setDate(6, Date.valueOf(consumption.getStart_date()));
            preparedStatement.setDate(7, Date.valueOf(consumption.getEnd_date()));
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("consumption added");
                return true;
            } else {
                System.out.println("consumption failed to add");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
