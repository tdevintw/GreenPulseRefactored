package Services.Implementations;

import Domain.*;
import Repository.UserRepository;
import Services.*;
import utils.TotalConsumption;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class UserServiceImpl implements UserService {

    private static UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepository();
    }

    @Override
    public User getUser(int id) {
        return userRepository.getUser(id);
    }


    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }


    @Override
    public boolean delete(User user) {
        return userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public List<User> filterUsersWithMoreThen3000() {
        return getAllUsers().stream().filter(user -> TotalConsumption.TotalConsumptionOfUser(user)>=3000).collect(Collectors.toList());

    }

    @Override
    public List<User> usersWithNoConsumption(LocalDate startDate, LocalDate endDate) {

        return getAllUsers().stream().
                filter(user -> TotalConsumption.TotalConsumptionOfUserWithFilter(user,startDate , endDate) == 0).collect(Collectors.toList());
    }


    @Override
    public void filterUsersByConsumptionTotal() {
         getAllUsers().stream().sorted((user1, user2) -> Double.compare(TotalConsumption.TotalConsumptionOfUser(user2), TotalConsumption.TotalConsumptionOfUser(user1))).collect(Collectors.toList())
                 .forEach(user -> System.out.println(user.toString() + ", With a total of carbon : " + TotalConsumption.TotalConsumptionOfUser(user) ));
    }


}
