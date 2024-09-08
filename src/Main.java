//import Domain.User;
//import Repository.*;
//import Services.ConsumptionService;
//import Services.Implementations.*;
//import Auth.*;
//import Services.UserInterface;
//import java.sql.*;
//import java.util.Scanner;

import Auth.Login;
import Auth.Register;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

//    private static UserInterface userService = new UserServiceImpl();
//    private static UserRepository userRepository = new UserRepository(userService);
//    private static ConsumptionService consumptionService = new ConsumptionServiceImpl();
//    private static ConsumptionRepository consumptionRepository = new ConsumptionRepository();

//    private static User currentUser;

    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("your name");
        String name = input.nextLine();
        System.out.println("your password");
        String password = input.nextLine();
        Login.isUserExist(name, password);

    }

//    public static void notAuthMenu() {
//        Scanner input = new Scanner(System.in);
//        logo();
//        System.out.println("Welcome to GreenPulse");
//        System.out.println("1-Login 2-Register");
//        int choice = input.nextInt();
//        while (choice != 1 && choice != 2) {
//            System.out.println("Choose a valid option");
//            choice = input.nextInt();
//        }
//        switch (choice) {
//            case 1:
//                handleLogin();
//                break;
//            case 2:
//                handleRegister();
//                break;
//        }
//
//    }
//
//    public static void authMenu() {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Welcome Back " + currentUser.getName());
//        System.out.println(
//                """
//                         1-Update Personal Information \n
//                         2-Add Consumption \n
//                         3-Filter Users By consumption Total \n
//                         4-Create Report \n
//                         5-Calculate Average of Consumption in a range of date \n
//                         6-My Consumptions \n
//                         7-My Reports \n
//                         8-Filter Users with 3000 KG CO2 \n
//                         9-Inactive users \n
//                         10-Logout \n
//                         11-Delete Account \n
//                        """
//        );
//        int choice = input.nextInt();
//        switch (choice) {
//            case 1:
//                updatePersonalInformation();
//                break;
//            case 2:
//                addConsumption();
//                break;
//            case 3:
//                filterUsersByConsumption();
//                break;
//            case 4:
//                createReport();
//                break;
//            case 5:
//                calculateOfConsumptionInARangeOfDate();
//                break;
//            case 6:
//                myConsumptions();
//                break;
//            case 7:
//                myReports();
//                break;
//            case 8:
//                filterUserWithMoreConsumptionThen3000KG();
//                break;
//            case 9:
//                getInactiveUsers();
//                break;
//            case 10:
//                currentUser = null;
//                break;
//            case 11:
//                deleteAccount();
//                break;
//            default:
//                System.out.println("Choose a valid option");
//                authMenu();
//                break;
//
//        }
//        main(null);
//    }
//
//    public static void logo() {
//        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
//                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡰⣼⢯⣟⡿⣯⠱⣌⢻⡿⣽⢯⢿⡄⢆⠀⡀⢀⠀⡀⢀⠀⡀⢀⠀⡀⢀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀          \n" +
//                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⢡⣹⢷⣻⢾⡽⢇⡳⣌⠧⢡⣟⡾⣏⡞⢠⠃⠠⠀⠄⠠⠀⠄⠠⠀⠄⠠⠀⡈⠠⠀⠠⠁⠈⢀⠈⢀⠈⠀         \n" +
//                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣢⠽⣏⣯⣟⢂⡇⣲⠱⣊⢧⣻⡽⣽⡜⣡⢊⠁⡈⢀⠐⠀⡐⠠⠐⡀⢂⠁⡀⠐⡀⠁⡀⠁⡄⡌⣄⠂⠄          \n" +
//                "⢲⣕⠢⢄⠠⢀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡁⢦⢿⡽⣾⡽⣎⠱⣥⠓⣍⠺⢰⣟⣧⢿⡰⢌⠒⡰⠀⠄⢂⠠⢁⠒⡄⢂⠆⡐⠡⠐⡠⢀⣙⣾⢳⣮⡜⣬      \n" +
//                "⢌⡙⢯⢎⡖⣰⢮⣴⣡⢆⡀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⢯⣟⢾⣳⣃⢮⡕⣎⡹⢌⡐⠎⣽⣞⣯⢷⢯⡖⡡⡘⡐⠢⢄⠣⡘⠰⡈⠤⣁⢣⣜⡴⣯⣻⣼⣻⢮⡻⡔      \n" +
//                "⠌⡜⣱⢮⣞⡽⣯⢶⣯⠿⣜⠤⢂⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢢⣹⢟⡾⣯⢷⡞⢳⡞⣶⡱⣂⢌⢳⣻⣞⣧⡟⣯⢚⠡⡐⣉⠒⢌⠂⡅⢣⣜⣲⣴⣻⢮⢿⣵⣻⠶⢏⠣⣵⡍      \n" +
//                "⠈⢒⡹⣟⡾⣽⡓⣮⢯⠿⣽⣻⣞⣦⢧⡐⡀⠀⠀⠀⠀⠀⠀⠠⣂⣿⢫⡿⡝⡏⡘⢰⡙⣧⡳⣍⠎⡐⡻⣾⣵⣻⢦⡍⠶⡱⢤⣉⢆⡳⣬⢷⣞⣧⣟⣞⣯⡟⠾⠉⡐⢂⣽⢷⣻      \n" +
//                "⠀⢥⣚⡽⣞⣷⣻⣼⢨⠳⣡⠳⣛⢾⣯⢷⢧⣎⡰⠈⠄⠀⠂⣁⠒⣯⣟⣳⢿⡵⣌⢦⡙⢶⡹⣄⠢⠅⠳⣟⡶⣯⢷⣞⣯⠵⣓⣮⣟⣳⢯⡷⣞⣳⣾⢹⠞⠁⡜⠠⠑⣸⣯⢯⡷\n" +
//                "⠀⢢⢙⣯⡽⣶⢻⡾⣴⣹⢆⡱⡘⢮⣌⠿⣟⡾⣽⡏⠤⢁⠂⠤⣙⡼⡾⣽⡛⡬⠙⠦⣫⣼⣻⠆⠄⡈⣇⡿⣽⡞⣯⣞⡾⣵⡻⣞⡼⣯⢷⣻⡽⢳⠉⠀⢀⠂⠤⣁⢻⡾⣯⡟⣽\n" +
//                "⠀⠀⠎⡜⡿⣵⢯⣟⡷⣇⣯⠵⣙⠦⠜⢡⢃⢻⢷⣍⣂⣭⣎⡽⣯⢷⣻⢷⡗⣀⠈⢤⣱⢯⡷⣩⡐⣐⡈⣿⣳⡟⣧⣟⢾⣳⡟⣽⢯⡷⠏⡱⡉⢄⠆⠀⢀⡀⢂⠶⣾⣻⣵⡻⢃\n" +
//                "⠀⠈⠐⡌⢿⣭⣟⢾⣽⣻⣜⢹⢮⡜⣥⡔⢊⠔⡂⢿⢻⣞⡾⣽⣳⢯⣻⢿⠆⠁⠓⡬⣼⣏⡿⡵⠎⡑⡨⣿⣳⣟⣧⡟⣯⢶⣟⢏⠏⠀⠀⢰⣩⠜⡂⠐⡠⣘⠹⣞⣷⣻⢶⡏⠄\n" +
//                "⠀⠀⠀⠌⢣⢟⣞⣯⢶⣻⣞⡎⢣⢟⡴⣯⣭⠘⣀⠢⡝⠏⣟⣧⢿⣹⢯⣿⡆⠈⠀⠌⡅⣿⡬⢑⠠⢀⠆⣿⣳⢯⡾⣽⢯⠟⠎⢨⠀⢀⠐⢧⠒⡐⣨⠱⢀⢴⣾⣟⡾⡽⢞⠹⠀\n" +
//                "⠀⠀⠀⢈⠐⢪⠽⣞⣯⢷⣫⢿⣆⠿⣜⡳⣞⡿⢦⣋⠄⡐⠜⢫⣿⣭⡿⣳⣤⣤⣤⣘⠢⡇⠇⢈⣤⣤⣬⣍⠻⢯⡷⡯⠋⠀⠀⠠⢉⡠⠐⢆⡠⡥⣃⢳⢰⣞⡷⡾⣝⠃⠈⠀⠀\n" +
//                "⠀⠀⠀⠀⠈⠄⠛⣽⠾⣭⢿⣹⢾⣳⡌⡳⣝⣾⣋⣷⠀⣂⠙⡦⢝⢯⡞⠁⣀⠀⡀⠈⢳⠁⡼⠋⢀⡀⠠⢈⢱⡌⠛⢀⠰⠀⢀⢨⠗⣡⢒⡎⡖⠣⢡⣭⡾⣽⠾⡙⠡⠀⠀⠀⠀\n" +
//                "⠀⠀⠀⠀⠀⠀⠐⢀⠫⣝⣯⢯⣟⡷⣇⡽⡙⢶⠻⣞⡟⢢⢋⠰⡈⢸⠀⠈⠛⡀⠄⠡⠈⠣⡇⢀⠙⠃⡐⠠⠀⡇⠀⢣⠘⣤⡜⣳⢞⡶⡜⠜⠐⢠⣾⣟⡗⠁⠂⠀⠀⠀⠀⠀⠀\n" +
//                " ⠀⠀⠀⢀⠀⡀⠂⠠⢡⢉⠌⡛⢾⣽⣻⢷⣌⢬⢱⡼⣹⣍⡷⡄⢀⠘⣦⡁⠂⡀⠄⢂⡼⢁⢳⡀⠌⠐⡀⠄⣡⠃⠀⣆⣿⢳⡾⣵⣮⠕⢂⢨⣴⣿⡳⠍⢀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
//                "⢀⠠⢀⠂⠠⢀⠁⠐⡀⠌⢂⢉⠶⣸⣏⣿⣻⣧⣟⣒⠳⣔⡿⡽⣮⠒⢈⣙⠷⠶⢞⣋⣴⣿⣦⣝⡛⠶⠖⣋⡁⢴⢳⣏⣦⢻⡜⣣⠔⡈⣶⡾⡙⣎⠹⢉⠒⡌⢢⠁⠄⠀⠀⠀⠀\n" +
//                "⣟⣾⣣⢮⣱⢤⣎⣴⣰⢎⣦⣹⣞⡷⢯⣶⣻⢾⣽⣻⣬⡇⢟⡳⡍⢠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡈⠲⠚⡘⢃⢉⣒⣶⣻⠷⣮⣑⢦⣀⢤⣀⠴⣡⡿⣾⣥⣒⢄⡀\n" +
//                "⡿⣶⢯⣟⢽⡛⢎⠳⠯⠛⠷⠻⠾⠽⡛⠷⠿⣿⣾⣿⣿⣿⣷⡎⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⢢⣡⣬⣿⣿⣯⣟⠿⠓⢫⠃⠘⠈⠀⠈⠉⠉⠁⢉⢁⡠⣄\n" +
//                "⣿⣹⡟⣾⣳⣭⣞⣖⣲⡙⢖⢣⠎⠶⣉⣖⣁⠒⣖⣩⠉⡝⠛⢱⣿⣿⣿⣿⣿⣿⣿⡟⣛⣙⣻⣙⣿⣿⣿⣿⣿⣿⣿⣎⠛⠟⠋⣉⢤⢊⠦⡙⠀⠃⠠⠀⠁⠈⠀⣀⣤⡴⢋⠐⠉\n" +
//                "⠓⡤⣙⢾⣵⡻⢾⡽⣷⢯⣚⡠⢉⡆⢴⢠⢋⡩⢙⠶⣳⠔⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣬⠻⢿⣿⣿⣿⣿⣿⣿⡆⠀⠱⠊⠌⠁⠁⠀⠀⠀⢀⣀⣰⣲⣿⣿⣹⠄⠢⠈⠀\n" +
//                "⠈⡐⠡⠚⣵⣻⢯⢿⡽⣻⣟⡿⣯⣿⣾⣄⣥⣎⠵⢋⠲⠁⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡙⢿⣿⣿⣿⣿⣿⠀⠀⠠⠀⠄⡀⠀⠠⣶⢾⡽⣯⠟⡞⠡⠃⠀⠀⠐⠀\n" +
//                "⠀⠀⠄⠁⠦⠹⠯⠟⠾⠳⢯⢽⣳⢯⠿⣽⣻⢾⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡉⢿⠿⠉⠛⢠⣬⣶⣶⣶⡶⣿⠿⣽⣫⠿⡝⠚⠀⠁⠈⠀⠀⠀⠀\n" +
//                "⠀⠀⠀⠂⠀⡁⠐⠈⠄⠃⡌⠢⠍⡞⢿⣳⢯⡿⣽⣿⠿⠟⠁⠙⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠄⠀⡴⠆⣽⢯⣟⡳⢟⠡⠋⠅⢃⠂⠈⠀⠀⠀⠁⠀⠀⠀⠀\n" +
//                "⠀⠀⠀⢀⠀⠀⠀⠂⢈⠐⠌⡑⣘⢮⣟⣳⣯⡿⢛⠉⡰⢀⠀⢂⣠⣴⣮⣭⣭⣙⣛⣛⣛⣛⣛⣛⣛⣛⣹⣅⡀⠀⠀⠀⠀⠀⠙⠛⠮⣑⠂⠤⠑⡈⠄⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀\n" +
//                "⠀⠀⠀⠀⠀⠀⠠⠁⠀⢌⠲⣱⢮⣟⡾⡽⠋⣁⣠⣵⣶⣶⣿⣿⢿⣟⣿⣻⢿⣿⣿⣿⣜⣿⣿⣿⢿⡿⣟⣿⣿⣶⣦⣠⡄⠀⠀⠀⠀⠈⠄⢂⠱⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
//                "⠀⠀⠀⠂⠀⠁⠀⡀⠁⢂⠭⣙⠯⢾⣝⣢⣾⣿⠻⡝⣾⢭⠟⡞⡹⢎⠳⢯⡿⣾⣽⣻⢯⣟⡾⣽⠻⡜⣍⢚⢳⢋⠗⣣⠹⣩⠛⡔⢢⠄⣀⠈⠤⣁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
//                "⠀⠀⠀⢀⠀⠀⠀⠀⠐⠈⠠⠁⢊⠑⢎⠛⠁⠠⠁⡘⢨⠁⠘⠠⠑⡈⠱⠈⡝⣷⣻⢎⡟⡼⡙⢌⠓⢨⠐⠌⠂⠡⠈⠀⠁⠀⠡⠈⠀⠐⠈⠌⠒⡄⢢⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
//                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠌⠀⠂⠀⠀⠀⠐⠀⠂⠁⠀⠀⠐⠠⢁⡘⠲⡙⢎⠴⣡⠙⡄⢈⠀⠌⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠀⠂⠌⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
//                "\n");
//
//    }
//
//    public static void handleRegister() {
//        logo();
//        Scanner input = new Scanner(System.in);
//        Scanner inputInt = new Scanner(System.in);
//        System.out.println("Enter Your name");
//        String name = input.nextLine();
//        System.out.println("Enter Your Your password");
//        String password = input.nextLine();
//        System.out.println("Enter Your Your Age");
//        int age = input.nextInt();
//
//        while (Register.createUser(name, password, age) == null) {
//            handleRegister();
//        }
//
//    }
//
//    public static void handleLogin() {
//        logo();
//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter Your Name");
//        String name = input.nextLine();
//        while (Login.isUserExist(name) == null) {
//            System.out.println("Try Another Name");
//            handleLogin();
//        }
//        String password;
//        do {
//            System.out.println("Enter Your Password");
//            password = input.nextLine();
//        }
//        while (!Login.isPasswordExist(Login.isUserExist(name), password));
//        currentUser = Login.isUserExist(name);
//    }
//
//    public static void updatePersonalInformation() {
//        logo();
//        Scanner input = new Scanner(System.in);
//        Scanner inputInt = new Scanner(System.in);
//        System.out.println(
//                """
//                        1-Name
//                        2-Password
//                        3-Age
//                        """
//        );
//        int choice = inputInt.nextInt();
//        switch (choice) {
//            case 1:
//                String newName;
//                do {
//                    System.out.println("Enter the new name");
//                    newName = input.nextLine();
//                } while (!Register.isNameTrue(newName));
//                currentUser.setName(newName);
//                userRepository.update(currentUser);
//                break;
//            case 2:
//                String newPassword;
//                do {
//                    System.out.println("Enter the new password");
//                    newPassword = input.nextLine();
//                } while (!Register.isPasswordTrue(newPassword));
//                currentUser.setName(newPassword);
//                userRepository.update(currentUser);
//            case 3:
//                int newAge;
//                do {
//                    System.out.println("Enter the new age");
//                    newAge = inputInt.nextInt();
//                } while (!Register.isAgeTrue(newAge));
//                currentUser.setAge(newAge);
//                userRepository.update(currentUser);
//            default:
//                System.out.println("enter a valid option");
//                updatePersonalInformation();
//        }
//        main(null);
//    }
//
//    public static void deleteAccount() {
//        userRepository.delete(currentUser);
//        currentUser = null;
//        main(null);
//    }
//
//    public static void seeAllUsers() {
//        userRepository.getAllUsers().stream().forEach(user -> System.out.println("Name : " + user.getName() + "Age : " + user.getAge()));
//        main(null);
//    }
//
//    public static void addConsumption() {
//    }
//
//
//    public static void filterUsersByConsumption() {
//    }
//
//
//    public static void createReport() {
//    }
//
//
//    public static void calculateOfConsumptionInARangeOfDate() {
//    }
//
//
//    public static void myConsumptions() {
//    }
//
//
//    public static void myReports() {
//    }
//
//
//    public static void filterUserWithMoreConsumptionThen3000KG() {
//    }
//
//
//    public static void getInactiveUsers() {
//    }
//

}