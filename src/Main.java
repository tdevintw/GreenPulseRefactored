
import Auth.Login;
import Auth.Register;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import Domain.*;
import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;
import Services.Implementations.*;
import Services.*;

public class Main {


    private static User currentUser;
    private static UserService userService = new UserServiceImpl();
    private static ConsumptionService consumptionService = new ConsumptionServiceImpl();


    public static void main(String[] args) throws SQLException {
        while (currentUser == null) {
            notAuthMenu();
        }
        authMenu();
    }

    public static void notAuthMenu() throws SQLException {
        Scanner input = new Scanner(System.in);
        logo();
        System.out.println("Welcome to GreenPulse");
        System.out.println("1-Login 2-Register");
        int choice = input.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Choose a valid option");
            choice = input.nextInt();
        }
        switch (choice) {
            case 1:
                handleLogin();
                break;
            case 2:
                handleRegister();
                break;
        }

    }

    public static void authMenu() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome Back " + currentUser.getName());
        System.out.println(
                """
                         1-Update Personal Information \n
                         2-Add Consumption \n
                         3-Filter Users By consumption Total \n
                         4-Create Report \n
                         5-Calculate Average of Consumption in a range of date \n
                         6-My Consumptions \n
                         7-My Reports \n
                         8-Filter Users with 3000 KG CO2 \n
                         9-Inactive users \n
                         10-Logout \n
                         11-Delete Account \n
                        """
        );
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                updatePersonalInformation();
                break;
            case 2:
                addConsumption();
                break;
            case 3:
                filterUsersByConsumption();
                break;
            case 4:
                createReport();
                break;
            case 5:
                calculateOfConsumptionInARangeOfDate();
                break;
            case 6:
                myConsumptions();
                break;
            case 7:
                myReports();
                break;
            case 8:
                filterUsersWithMoreConsumptionThen3000KG();
                break;
            case 9:
                getInactiveUsers();
                break;
            case 10:
                currentUser = null;
                break;
            case 11:
                deleteAccount();
                break;
            default:
                System.out.println("Choose a valid option");
                authMenu();
                break;

        }
        main(null);
    }

    public static void logo() {
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡰⣼⢯⣟⡿⣯⠱⣌⢻⡿⣽⢯⢿⡄⢆⠀⡀⢀⠀⡀⢀⠀⡀⢀⠀⡀⢀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀          \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⢡⣹⢷⣻⢾⡽⢇⡳⣌⠧⢡⣟⡾⣏⡞⢠⠃⠠⠀⠄⠠⠀⠄⠠⠀⠄⠠⠀⡈⠠⠀⠠⠁⠈⢀⠈⢀⠈⠀         \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣢⠽⣏⣯⣟⢂⡇⣲⠱⣊⢧⣻⡽⣽⡜⣡⢊⠁⡈⢀⠐⠀⡐⠠⠐⡀⢂⠁⡀⠐⡀⠁⡀⠁⡄⡌⣄⠂⠄          \n" +
                "⢲⣕⠢⢄⠠⢀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡁⢦⢿⡽⣾⡽⣎⠱⣥⠓⣍⠺⢰⣟⣧⢿⡰⢌⠒⡰⠀⠄⢂⠠⢁⠒⡄⢂⠆⡐⠡⠐⡠⢀⣙⣾⢳⣮⡜⣬      \n" +
                "⢌⡙⢯⢎⡖⣰⢮⣴⣡⢆⡀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⢯⣟⢾⣳⣃⢮⡕⣎⡹⢌⡐⠎⣽⣞⣯⢷⢯⡖⡡⡘⡐⠢⢄⠣⡘⠰⡈⠤⣁⢣⣜⡴⣯⣻⣼⣻⢮⡻⡔      \n" +
                "⠌⡜⣱⢮⣞⡽⣯⢶⣯⠿⣜⠤⢂⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢢⣹⢟⡾⣯⢷⡞⢳⡞⣶⡱⣂⢌⢳⣻⣞⣧⡟⣯⢚⠡⡐⣉⠒⢌⠂⡅⢣⣜⣲⣴⣻⢮⢿⣵⣻⠶⢏⠣⣵⡍      \n" +
                "⠈⢒⡹⣟⡾⣽⡓⣮⢯⠿⣽⣻⣞⣦⢧⡐⡀⠀⠀⠀⠀⠀⠀⠠⣂⣿⢫⡿⡝⡏⡘⢰⡙⣧⡳⣍⠎⡐⡻⣾⣵⣻⢦⡍⠶⡱⢤⣉⢆⡳⣬⢷⣞⣧⣟⣞⣯⡟⠾⠉⡐⢂⣽⢷⣻      \n" +
                "⠀⢥⣚⡽⣞⣷⣻⣼⢨⠳⣡⠳⣛⢾⣯⢷⢧⣎⡰⠈⠄⠀⠂⣁⠒⣯⣟⣳⢿⡵⣌⢦⡙⢶⡹⣄⠢⠅⠳⣟⡶⣯⢷⣞⣯⠵⣓⣮⣟⣳⢯⡷⣞⣳⣾⢹⠞⠁⡜⠠⠑⣸⣯⢯⡷\n" +
                "⠀⢢⢙⣯⡽⣶⢻⡾⣴⣹⢆⡱⡘⢮⣌⠿⣟⡾⣽⡏⠤⢁⠂⠤⣙⡼⡾⣽⡛⡬⠙⠦⣫⣼⣻⠆⠄⡈⣇⡿⣽⡞⣯⣞⡾⣵⡻⣞⡼⣯⢷⣻⡽⢳⠉⠀⢀⠂⠤⣁⢻⡾⣯⡟⣽\n" +
                "⠀⠀⠎⡜⡿⣵⢯⣟⡷⣇⣯⠵⣙⠦⠜⢡⢃⢻⢷⣍⣂⣭⣎⡽⣯⢷⣻⢷⡗⣀⠈⢤⣱⢯⡷⣩⡐⣐⡈⣿⣳⡟⣧⣟⢾⣳⡟⣽⢯⡷⠏⡱⡉⢄⠆⠀⢀⡀⢂⠶⣾⣻⣵⡻⢃\n" +
                "⠀⠈⠐⡌⢿⣭⣟⢾⣽⣻⣜⢹⢮⡜⣥⡔⢊⠔⡂⢿⢻⣞⡾⣽⣳⢯⣻⢿⠆⠁⠓⡬⣼⣏⡿⡵⠎⡑⡨⣿⣳⣟⣧⡟⣯⢶⣟⢏⠏⠀⠀⢰⣩⠜⡂⠐⡠⣘⠹⣞⣷⣻⢶⡏⠄\n" +
                "⠀⠀⠀⠌⢣⢟⣞⣯⢶⣻⣞⡎⢣⢟⡴⣯⣭⠘⣀⠢⡝⠏⣟⣧⢿⣹⢯⣿⡆⠈⠀⠌⡅⣿⡬⢑⠠⢀⠆⣿⣳⢯⡾⣽⢯⠟⠎⢨⠀⢀⠐⢧⠒⡐⣨⠱⢀⢴⣾⣟⡾⡽⢞⠹⠀\n" +
                "⠀⠀⠀⢈⠐⢪⠽⣞⣯⢷⣫⢿⣆⠿⣜⡳⣞⡿⢦⣋⠄⡐⠜⢫⣿⣭⡿⣳⣤⣤⣤⣘⠢⡇⠇⢈⣤⣤⣬⣍⠻⢯⡷⡯⠋⠀⠀⠠⢉⡠⠐⢆⡠⡥⣃⢳⢰⣞⡷⡾⣝⠃⠈⠀⠀\n" +
                "⠀⠀⠀⠀⠈⠄⠛⣽⠾⣭⢿⣹⢾⣳⡌⡳⣝⣾⣋⣷⠀⣂⠙⡦⢝⢯⡞⠁⣀⠀⡀⠈⢳⠁⡼⠋⢀⡀⠠⢈⢱⡌⠛⢀⠰⠀⢀⢨⠗⣡⢒⡎⡖⠣⢡⣭⡾⣽⠾⡙⠡⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠐⢀⠫⣝⣯⢯⣟⡷⣇⡽⡙⢶⠻⣞⡟⢢⢋⠰⡈⢸⠀⠈⠛⡀⠄⠡⠈⠣⡇⢀⠙⠃⡐⠠⠀⡇⠀⢣⠘⣤⡜⣳⢞⡶⡜⠜⠐⢠⣾⣟⡗⠁⠂⠀⠀⠀⠀⠀⠀\n" +
                " ⠀⠀⠀⢀⠀⡀⠂⠠⢡⢉⠌⡛⢾⣽⣻⢷⣌⢬⢱⡼⣹⣍⡷⡄⢀⠘⣦⡁⠂⡀⠄⢂⡼⢁⢳⡀⠌⠐⡀⠄⣡⠃⠀⣆⣿⢳⡾⣵⣮⠕⢂⢨⣴⣿⡳⠍⢀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢀⠠⢀⠂⠠⢀⠁⠐⡀⠌⢂⢉⠶⣸⣏⣿⣻⣧⣟⣒⠳⣔⡿⡽⣮⠒⢈⣙⠷⠶⢞⣋⣴⣿⣦⣝⡛⠶⠖⣋⡁⢴⢳⣏⣦⢻⡜⣣⠔⡈⣶⡾⡙⣎⠹⢉⠒⡌⢢⠁⠄⠀⠀⠀⠀\n" +
                "⣟⣾⣣⢮⣱⢤⣎⣴⣰⢎⣦⣹⣞⡷⢯⣶⣻⢾⣽⣻⣬⡇⢟⡳⡍⢠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡈⠲⠚⡘⢃⢉⣒⣶⣻⠷⣮⣑⢦⣀⢤⣀⠴⣡⡿⣾⣥⣒⢄⡀\n" +
                "⡿⣶⢯⣟⢽⡛⢎⠳⠯⠛⠷⠻⠾⠽⡛⠷⠿⣿⣾⣿⣿⣿⣷⡎⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⢢⣡⣬⣿⣿⣯⣟⠿⠓⢫⠃⠘⠈⠀⠈⠉⠉⠁⢉⢁⡠⣄\n" +
                "⣿⣹⡟⣾⣳⣭⣞⣖⣲⡙⢖⢣⠎⠶⣉⣖⣁⠒⣖⣩⠉⡝⠛⢱⣿⣿⣿⣿⣿⣿⣿⡟⣛⣙⣻⣙⣿⣿⣿⣿⣿⣿⣿⣎⠛⠟⠋⣉⢤⢊⠦⡙⠀⠃⠠⠀⠁⠈⠀⣀⣤⡴⢋⠐⠉\n" +
                "⠓⡤⣙⢾⣵⡻⢾⡽⣷⢯⣚⡠⢉⡆⢴⢠⢋⡩⢙⠶⣳⠔⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣬⠻⢿⣿⣿⣿⣿⣿⣿⡆⠀⠱⠊⠌⠁⠁⠀⠀⠀⢀⣀⣰⣲⣿⣿⣹⠄⠢⠈⠀\n" +
                "⠈⡐⠡⠚⣵⣻⢯⢿⡽⣻⣟⡿⣯⣿⣾⣄⣥⣎⠵⢋⠲⠁⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡙⢿⣿⣿⣿⣿⣿⠀⠀⠠⠀⠄⡀⠀⠠⣶⢾⡽⣯⠟⡞⠡⠃⠀⠀⠐⠀\n" +
                "⠀⠀⠄⠁⠦⠹⠯⠟⠾⠳⢯⢽⣳⢯⠿⣽⣻⢾⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡉⢿⠿⠉⠛⢠⣬⣶⣶⣶⡶⣿⠿⣽⣫⠿⡝⠚⠀⠁⠈⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠂⠀⡁⠐⠈⠄⠃⡌⠢⠍⡞⢿⣳⢯⡿⣽⣿⠿⠟⠁⠙⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠄⠀⡴⠆⣽⢯⣟⡳⢟⠡⠋⠅⢃⠂⠈⠀⠀⠀⠁⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢀⠀⠀⠀⠂⢈⠐⠌⡑⣘⢮⣟⣳⣯⡿⢛⠉⡰⢀⠀⢂⣠⣴⣮⣭⣭⣙⣛⣛⣛⣛⣛⣛⣛⣛⣹⣅⡀⠀⠀⠀⠀⠀⠙⠛⠮⣑⠂⠤⠑⡈⠄⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠠⠁⠀⢌⠲⣱⢮⣟⡾⡽⠋⣁⣠⣵⣶⣶⣿⣿⢿⣟⣿⣻⢿⣿⣿⣿⣜⣿⣿⣿⢿⡿⣟⣿⣿⣶⣦⣠⡄⠀⠀⠀⠀⠈⠄⢂⠱⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠂⠀⠁⠀⡀⠁⢂⠭⣙⠯⢾⣝⣢⣾⣿⠻⡝⣾⢭⠟⡞⡹⢎⠳⢯⡿⣾⣽⣻⢯⣟⡾⣽⠻⡜⣍⢚⢳⢋⠗⣣⠹⣩⠛⡔⢢⠄⣀⠈⠤⣁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢀⠀⠀⠀⠀⠐⠈⠠⠁⢊⠑⢎⠛⠁⠠⠁⡘⢨⠁⠘⠠⠑⡈⠱⠈⡝⣷⣻⢎⡟⡼⡙⢌⠓⢨⠐⠌⠂⠡⠈⠀⠁⠀⠡⠈⠀⠐⠈⠌⠒⡄⢢⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠌⠀⠂⠀⠀⠀⠐⠀⠂⠁⠀⠀⠐⠠⢁⡘⠲⡙⢎⠴⣡⠙⡄⢈⠀⠌⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠀⠂⠌⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "\n");

    }

    public static void handleRegister() throws SQLException {
        logo();
        Scanner input = new Scanner(System.in);
        Scanner inputInt = new Scanner(System.in);
        System.out.println("Enter Your name");
        String name = input.nextLine();
        System.out.println("Enter Your Your password");
        String password = input.nextLine();
        System.out.println("Enter Your Your Age");
        int age = input.nextInt();
        Register register = new Register();
        User user = register.createUser(name, password, age);
        if (user == null) {
            System.out.println("Register failed");
        } else {
            System.out.println("now login");
            handleLogin();
        }
    }

    public static void handleLogin() {
        logo();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Your Name");
        String name = input.nextLine();
        System.out.println("Enter Your password");
        String password = input.nextLine();
        User user = Login.isUserExist(name, password);
        if (user == null) {
            handleLogin();
        } else {
            currentUser = user;
        }
    }

    public static void updatePersonalInformation() throws SQLException {
        logo();
        Scanner input = new Scanner(System.in);
        Scanner inputInt = new Scanner(System.in);
        System.out.println(
                """
                        1-Name
                        2-Password
                        3-Age
                        """
        );
        int choice = inputInt.nextInt();
        switch (choice) {
            case 1:
                String newName;
                do {
                    System.out.println("Enter the new name");
                    newName = input.nextLine();
                } while (!Register.isNameValid(newName));
                currentUser.setName(newName);
                userService.update(currentUser);
                break;
            case 2:
                String newPassword;
                do {
                    System.out.println("Enter the new password");
                    newPassword = input.nextLine();
                } while (!Register.isPasswordValid(newPassword));
                currentUser.setPassword(newPassword);
                userService.update(currentUser);
                break;
            case 3:
                int newAge;
                do {
                    System.out.println("Enter the new age");
                    newAge = inputInt.nextInt();
                } while (!Register.isAgeValid(newAge));
                currentUser.setAge(newAge);
                userService.update(currentUser);
            default:
                System.out.println("enter a valid option");
                updatePersonalInformation();
        }
        main(null);
    }

    public static void deleteAccount() throws SQLException {
        userService.delete(currentUser);
        currentUser = null;
        main(null);
    }



    public static void addConsumption() throws SQLException {
        Scanner input = new Scanner(System.in);
        Scanner inputFloat = new Scanner(System.in);
        Scanner inputInt = new Scanner(System.in);

        System.out.println("What is Your Consumption Type : TRANSPORT-FOOD-ACCOMMODATION");
        String ConsumptionType = input.nextLine();
        ConsumptionType consumptionType = null;
        String impactType;
        AllTypesOfConsumption consumptionimpactType = null;
        int intParam = 0; //this param represent the distance or weight or hours
        switch (ConsumptionType) {
            case "TRANSPORT":
                consumptionType = Domain.Enum.ConsumptionType.TRANSPORT;
                System.out.println("Enter please Transport type : CAR , TRAIN");
                impactType = input.nextLine();
                if (impactType.equalsIgnoreCase("CAR")) {
                    consumptionimpactType = AllTypesOfConsumption.CAR;
                } else if (impactType.equalsIgnoreCase("TRAIN")) {
                    consumptionimpactType = AllTypesOfConsumption.TRAIN;
                } else {
                    System.out.println("impact doesnt exist");

                    addConsumption();
                }
                System.out.println("Enter distance traveled with " + impactType);
                intParam = inputInt.nextInt();
                break;
            case "FOOD":
                consumptionType = Domain.Enum.ConsumptionType.FOOD;
                System.out.println("Enter please FOOD type : MEAT , VEGETABLE");
                impactType = input.nextLine();
                if (impactType.equalsIgnoreCase("MEAT")) {
                    consumptionimpactType = AllTypesOfConsumption.MEAT;
                } else if (impactType.equalsIgnoreCase("VEGETABLE")) {
                    consumptionimpactType = AllTypesOfConsumption.VEGETABLE;
                } else {
                    System.out.println("impact doesnt exist");
                    addConsumption();
                }
                System.out.println("Enter Weight of " + impactType);
                intParam = inputInt.nextInt();
                break;
            case "ACCOMMODATION":
                consumptionType = Domain.Enum.ConsumptionType.ACCOMMODATION;
                System.out.println("Enter please Transport type : ELECTRIC , GAZ");
                impactType = input.nextLine();
                if (impactType.equalsIgnoreCase("ELECTRIC")) {
                    consumptionimpactType = AllTypesOfConsumption.ELECTRIC;
                } else if (impactType.equalsIgnoreCase("GAZ")) {
                    consumptionimpactType = AllTypesOfConsumption.GAZ;
                } else {
                    System.out.println("impact doesnt exist");
                    addConsumption();
                }
                System.out.println("Enter hours of " + impactType + " consumption");
                intParam = inputInt.nextInt();
                break;
            default:
                System.out.println("Please Enter a valid Type");
                addConsumption();
        }

        System.out.println("Enter CarbonQuantity in KG");
        Float quantity = inputFloat.nextFloat();
        System.out.println("Enter Start Date : yyyy-MM-dd");
        String start_date = input.nextLine();
        System.out.println("Enter Start Date : yyyy-MM-dd");
        String end_date = input.nextLine();
        boolean isConsumptionAdded = consumptionService.add(consumptionType, currentUser, quantity, intParam, consumptionimpactType, LocalDate.parse(start_date), LocalDate.parse(end_date));
        main(null);
    }


    public static void filterUsersByConsumption() {
        userService.filterUsersByConsumptionTotal();
    }


    public static void createReport() {
    }


    public static void calculateOfConsumptionInARangeOfDate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter start date");
        String startDate = input.nextLine();
        System.out.println("Enter end date");
        String endDate = input.nextLine();
        System.out.println("total of consumption is : " + consumptionService.calculateAverageOfConsumptionWithinARange(currentUser, LocalDate.parse(startDate), LocalDate.parse(endDate)));
    }


    public static void myConsumptions() {
        for(Consumption consumption : consumptionService.getUserConsumptions(currentUser)){
            System.out.println(consumption.toString());
        }
    }


    public static void myReports() {
    }


    public static void filterUsersWithMoreConsumptionThen3000KG() {
        userService.filterUsersWithMoreThen3000().forEach(user -> System.out.println(user.toString()));
    }


    public static void getInactiveUsers() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter start date");
        String startDate = input.nextLine();
        System.out.println("Enter end date");
        String endDate = input.nextLine();
        userService.usersWithNoConsumption(LocalDate.parse(startDate), LocalDate.parse(endDate)).forEach(user -> System.out.println(user.toString()));
    }
}