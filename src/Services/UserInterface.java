package Services;

import Domain.User;

import java.time.LocalDate;
import java.util.List;

public interface UserInterface {
    User getUser(int id);

    boolean update(User user);

    boolean delete(User user);

    List<User> getAllUsers();

    List<User> filterUsersWithMoreThen3000(User usr , LocalDate startDate , LocalDate endDate);

    List<User> usersWithNoConsumption(LocalDate startDate , LocalDate endDate);


    List<User> filterUsersByConsumptionTotal();
}
