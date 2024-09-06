package Repository;

import Services.UserInterface;
import Domain.*;

import java.util.List;

public class UserRepository {
    private final UserInterface userService;

    public UserRepository(UserInterface userService){
        this.userService = userService;
    }

    public User getUser(int id){
        return userService.getUser(id);
    }

    public void update(User user){
        userService.update(user);
    }

    public void delete(User user){
        userService.update(user);
    }

    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
