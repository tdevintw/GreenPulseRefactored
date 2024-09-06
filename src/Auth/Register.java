package Auth;

import Database.Database;
import Domain.User;

public class Register {

    public static User createUser(String name, String password, int age) {
        if (checkUserInput(name, password, age)) {
            User newUser = new User(name, password, age);
            Database database = Database.getDatabase();
            try {
                database.setUser(newUser);
            } catch (Exception e) {
                System.err.println("Can't Register the user : " + e.getMessage());
            }
            return newUser;
        } else {
            System.out.println("Give it Another Shot");
            return null;
        }
    }

    public static boolean isNameTrue(String name) {

        return name.length() >= 3;
    }

    public static boolean isPasswordTrue(String password) {
        return password.length() > 5;
    }

    public static boolean isAgeTrue(int age) {
        return age > 0;
    }

    public static boolean checkUserInput(String name, String password, int age) {
        if (!isNameTrue(name)) {
            System.out.println("Name should be at least 3 characters");
            return false;
        } else if (!isPasswordTrue(password)) {
            System.out.println("Password must be at least 6 characters");
            return false;
        } else if (!isAgeTrue(age)) {
            System.out.println("Age must be greater then 0");
            return false;
        } else {
            return true;
        }

    }

}
