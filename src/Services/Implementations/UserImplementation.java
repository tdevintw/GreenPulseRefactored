package Services.Implementations;

import Services.Interfaces.UserInterface;
import Domain.*;
import  config.*;


public  class UserImplementation implements UserInterface {
//    @Override
//    public User getUser(int id) {
//        return Database.getDatabase().getUsers().stream().filter(user -> user.getId() == id).findFirst().orElse(null);
//    }

//    @Override
//    public void update(User user) {
//        int index = Database.getDatabase().getUsers().indexOf(user);
//        Database.getDatabase().getUsers().set(index, user);
//    }

//    @Override
//    public void delete(User user) { //choosing to delete with id  because remove(int index) is faster compared to remove(Object o)
//        Database.getDatabase().getUsers().remove(user);
//    }

//    @Override
//    public List<User> getAllUsers() {
//        return Database.getDatabase().getUsers();
//    }

}
