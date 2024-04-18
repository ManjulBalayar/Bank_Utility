import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class UtilitySystemMain {

    private static int acctNum, day;
    private static String username, password;


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int menuSelect = 0;
        int subMenuSelect = 0;
        System.out.println("Welcome to Utilities Services System!");
        System.out.println("Today is Day: " + Helper.getDay());

        while (true) {
            int choice;
            while (true) {
                System.out.println("\nPlease select an option:");
                System.out.println("1. Create an Account");
                System.out.println("2. Login to your Account");
                System.out.println("3. Exit");
                try {
                    choice = Integer.parseInt(scan.next());
                    if (choice < 1 || choice > 3) {
                        System.out.println("Invalid Input. Please enter a valid number.");
                    } else {
                        break;
                    }

                } catch (Exception e) {
                    System.out.println("Invalid Input");
                }
            }
            if (choice == 1) {
                System.out.println("-----Creating Account-----");
                System.out.println("Enter Username: ");
                username = scan.next();
                System.out.println("Enter password: ");
                password = scan.next();

                // Create a Random object
                Random random = new Random();

                // Generate a random 6-digit number
                acctNum = 100000 + random.nextInt(900000);

                Helper.setAccount();

                System.out.println("Account Successfully Created!");
                System.out.println("Your account number is: " + acctNum);
            } else if (choice == 2) {
//            	while (true) {
//                    System.out.println("Enter 1 to use username or 2 for account number: ");
//                    try {
//                        input = Integer.parseInt(scan.next());
//                        break;
//                    } catch (Exception e) {
//                        System.out.println("Invalid Input");
//                    }
//                    if(input == 1){
//                        int i;
//                        for(i = 0; i < 3; i++){
//                            System.out.println("Input username: ");
//                            String username = scan.next();
//                            System.out.println("Input password: ");
//                            String password = scan.next();
//                            if(utilAcc.checkPassword(password) && utilAcc.checkUsername(username)){
//                                System.out.println("Successful Sign-in");
//                                break;
//                            } else{
//                                System.out.println("Incorrect Username or Password");
//                            }
//                        }
//                        if(i >= 3){
//                            System.out.println("Too many unsuccessful login attempts.");
//                            System.out.println("Exiting program...");
//                            return;
//                        }
//                        break;
//                    }else if(input == 2){
//                        int i;
//                        for(i = 0; i < 3; i++){
//                            int number;
//                            while(true) {
//                                try {
//                                    System.out.println("Input account number: ");
//                                    number = Integer.parseInt(scan.next());
//                                    break;
//                                } catch (Exception e) {
//                                    System.out.println("Invalid Input");
//                                }
//                            }
//                            System.out.println("Input password: ");
//                            String password = scan.next();
//                            if(utilAcc.checkPassword(password) && utilAcc.checkAccountNum(number)){
//                                System.out.println("Successful Sign-in");
//                                break;
//                            } else{
//                                System.out.println("Incorrect Account Number or Password");
//                            }
//                        }
//                        if(i >= 3){
//                            System.out.println("Too many unsuccessful login attempts.");
//                            System.out.println("Exiting program...");
//                            return;
//                        }
//                        break;
//                    }else{
//                        System.out.println("Invalid Input");
//                    }
//                }
            } else if (choice == 3) {
                System.out.println("Thanks you! Come Again!");
                break;
            }

            // Main program loop
            while (true) {
                day = Helper.getDay();
                System.out.println("Day: " + day);
                System.out.println("Please select one of the following actions:");
                System.out.println("1: Go to the next Day");
                System.out.println("2: Access Utility Account");
                System.out.println("3: Quit");

                // Scan Menu Select Input
                while (true) {
                    System.out.print("Input: ");
                    while (true) {
                        try {
                            menuSelect = scan.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid Input");
                        }
                    }
                    System.out.println();
                    if (menuSelect < 1 || menuSelect > 3) {
                        System.out.println("Invalid Input");
                    } else {
                        break;
                    }
                }
                if (menuSelect == 1) { // Increment Day
                    day++;
                    Helper.setDay(day);
                } else if (menuSelect == 2) { // Utility Menu Access
                    while (true) {
                        System.out.println("Please select a Utility Account action below:");
                        System.out.println("1: Check Payment History");
                        System.out.println("2: Pay Upcoming Payment");
                        System.out.println("3: Exit");

                        // Scanning menu input select
                        while (true) {
                            while (true) {
                                try {
                                    System.out.print("Input: ");
                                    subMenuSelect = scan.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid Input");
                                }
                            }
                            System.out.println();
                            if (subMenuSelect < 1 || subMenuSelect > 3) {
                                System.out.println("Invalid Input");
                            } else {
                                break;
                            }
                        }

                        if (subMenuSelect == 1) { // Payment history

                        } else if (subMenuSelect == 2) { // Pay Payment

                            if (subMenuSelect == 1) {
//
                            }

                        } else {
                            break;
                        }
                    }
                } else {
                    System.out.println("Thank you! Come again!");
                    break;
                }
            }
        }
    }
}