package Auth;

import Databaseéz.Database;
import Domain.User;

import java.util.List;
import java.util.Optional;

public class Login {

    public static User isUserExist(String name) {
        List<User> users = Database.getDatabase().getUsers();
        Optional<User> searchedUser = users.stream().filter(user -> user.getName().equals(name)).findFirst();
        if (searchedUser.isPresent()) {
            return searchedUser.get();
        } else {
            System.out.println("Name Wasn't Found");
            return null;
        }
    }

    public static boolean isPasswordExist(User user, String password) {
        if (user.getPassword().equals(password)) {
            return true;
        } else {
            System.out.println("Try Again");
            return false;
        }
    }
}
