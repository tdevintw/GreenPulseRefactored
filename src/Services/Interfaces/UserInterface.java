package Services.Interfaces;

import Domain.User;

import java.util.List;

public interface UserInterface {
    User getUser(int id);

    void update(User user);

    void delete(User user);

    List<User> getAllUsers();
}
