package Repository;

import Domain.*;
import Domain.Enum.AllTypesOfConsumption;
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
            preparedStatement.setObject(4, consumption.getConsumptionType() , Types.OTHER);
            preparedStatement.setObject(5, consumption.getConsumptionImpactType() , Types.OTHER);
            preparedStatement.setDate(6, Date.valueOf(consumption.getStartDate()));
            preparedStatement.setDate(7, Date.valueOf(consumption.getEndDate()));
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

    public List<Consumption> getAllConsumptions() {
        String query = "SELECT * FROM consumptions";
        List<Consumption> allConsumptions = new ArrayList<>();
        UserRepository userRepository = new UserRepository();
        User user;
        try (Connection connection = Database.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet consumptionsSet = preparedStatement.executeQuery();
            Consumption consumption;
            while (consumptionsSet.next()) {
                int id = consumptionsSet.getInt("id");
                user = userRepository.getUser(consumptionsSet.getInt("user_id"));
                float carbonQuantity = consumptionsSet.getFloat("carbon_quantity");
                float impact = consumptionsSet.getFloat("impact");
                AllTypesOfConsumption impactType = AllTypesOfConsumption.valueOf(consumptionsSet.getString("consumption_impact_type"));
                LocalDate startDate = consumptionsSet.getDate("start_date").toLocalDate();
                LocalDate endDate = consumptionsSet.getDate("end_date").toLocalDate();

                consumption = switch (consumptionsSet.getString("consumption_type")) {
                    case "TRANSPORT" -> new Transport(id, user, carbonQuantity, impact, impactType, startDate, endDate);
                    case "FOOD" -> new Food(id, user, carbonQuantity, impact, impactType, startDate, endDate);
                    default -> new Accommodation(id, user, carbonQuantity, impact, impactType, startDate, endDate);
                };
                allConsumptions.add(consumption);
            }
        } catch (SQLException e) {
            {
                throw new RuntimeException(e);
            }
        }
        return allConsumptions;
    }

    public boolean update(Consumption consumption) {
        String query = "UPDATE  consumptions SET carbon_quantity = ?,impact = ?,start_date=?,end_date=? WHERE id = ?";
        try (Connection connection = Database.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setFloat(1, consumption.getCarbonQuantity());
            preparedStatement.setFloat(2, consumption.getImpact());
            preparedStatement.setDate(3, Date.valueOf(consumption.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(consumption.getEndDate()));
            preparedStatement.setInt(5, consumption.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("consumption updated");
                return true;
            }else{
                System.out.println("can't update consumption");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consumption> getUserConsumptions(int user_id){
        String query = "SELECT * FROM consumptions WHERE user_id = ?";
        List<Consumption> allUserConsumptions = new ArrayList<>();
        Consumption consumption ;
        UserRepository userRepository = new UserRepository();
        User user = userRepository.getUser(user_id);
        try (Connection connection = Database.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user_id);
            ResultSet userConsumptions = preparedStatement.executeQuery();
            while(userConsumptions.next()){
                int id = userConsumptions.getInt("id");
                float carbonQuantity = userConsumptions.getFloat("carbon_quantity");
                float impact = userConsumptions.getFloat("impact");
                AllTypesOfConsumption impactType = AllTypesOfConsumption.valueOf(userConsumptions.getString("consumption_impact_type"));
                LocalDate startDate = userConsumptions.getDate("start_date").toLocalDate();
                LocalDate endDate = userConsumptions.getDate("end_date").toLocalDate();
                consumption = switch (userConsumptions.getString("consumption_type")) {
                    case "TRANSPORT" -> new Transport(id, user, carbonQuantity, impact, impactType, startDate, endDate);
                    case "FOOD" -> new Food(id, user, carbonQuantity, impact, impactType, startDate, endDate);
                    default -> new Accommodation(id, user, carbonQuantity, impact, impactType, startDate, endDate);
                };
                allUserConsumptions.add(consumption);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUserConsumptions;
    }

    public boolean delete(Consumption consumption) {
        String query = "DELETE FROM consumptions WHERE id = ?";
        try (Connection connection = Database.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, consumption.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Consumptions was deleted");
                return true;
            } else {
                System.out.println("Consumption fail to delete");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
