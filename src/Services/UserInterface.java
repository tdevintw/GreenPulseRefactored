package Services;

import Domain.User;

import java.util.List;

public interface UserInterface {
    User getUser(int id);

    void update(User user);

    void delete(User user);

    List<User> getAllUsers();

    List<User> filterUsersWithMoreThen3000();

    List<User> filterUsersByConsumptionTotal();
}
