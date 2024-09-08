package Services;

import Domain.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User getUser(int id);

    boolean update(User user);

    boolean delete(User user);

    List<User> getAllUsers();

    List<User> filterUsersWithMoreThen3000();

    List<User> usersWithNoConsumption(LocalDate startDate , LocalDate endDate);


    void filterUsersByConsumptionTotal();
}
