package Repository;

import Services.UserInterface;
import Domain.*;
import config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {


    public  User getUser(int id) {
        Optional<User> user = getAllUsers().stream().filter(user1 -> user1.getId() == id).findFirst();
        if (user.isPresent()) {
            return user.get();
        } else {
            System.out.println("User not found ");
            return null;
        }
    }

    public boolean update(User user) {

        String query = "UPDATE users SET name = ? , password = ? , age = ? WHERE = ?";
        try (Connection connection = Database.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setInt(4, user.getId());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("user updated");
                return true;
            } else {
                System.out.println("fail to update user");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(User user) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Database.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User was deleted");
                return true;
            } else {
                System.out.println("User fail to delete");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        try (Connection connection = Database.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ConsumptionRepository consumptionRepository = new ConsumptionRepository();
            List<User> users = new ArrayList<>();
            User user;
            ResultSet allUsers = preparedStatement.executeQuery();
            while (allUsers.next()) {
                int id = allUsers.getInt("id");
                String username = allUsers.getString("name");
                String userPassword = allUsers.getString("password");
                int userAge = allUsers.getInt("age");
                user = new User(id, username, userPassword, userAge);
                user.setConsumptions(consumptionRepository.getUserConsumptions(id));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
