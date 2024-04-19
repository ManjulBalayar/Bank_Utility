import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class UtilitySystemMain {

    // Global variables to store account information
    private static int acctNum, day;
    private static String username, password;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int choice = 0;
        int subChoice = 0;
        System.out.println("Welcome to Utilities Services System!");
        System.out.println("Today is Day: " + Helper.getDay());

        while (true) {
            // Main menu loop
            while (true) {
                // Display main menu options
                System.out.println("\nPlease select an option:");
                System.out.println("1. Create an Account");
                System.out.println("2. Login to your Account");
                System.out.println("3. Exit");
                try {
                    choice = Integer.parseInt(scan.next()); // Get user choice
                    if (choice < 1 || choice > 3) {
                        System.out.println("Invalid Input. Please enter a valid number.");
                    } else {
                        break; // Break loop if valid choice
                    }

                } catch (Exception e) {
                    System.out.println("Invalid Input"); // Handle invalid input
                }
            }

            if (choice == 1) { // Create Account
                // Prompt user for account details
                System.out.println("-----Creating Account-----");
                System.out.println("Enter Username: ");
                username = scan.next();
                System.out.println("Enter password: ");
                password = scan.next();

                // Generate a random 6-digit account number
                Random random = new Random();
                acctNum = 100000 + random.nextInt(900000);

                // Set account information using Helper class
                Helper.setAccount(username, password, acctNum);

                System.out.println("Account Successfully Created!");
                System.out.println("Your account number is: " + acctNum);
            } else if (choice == 2) { // Login
                while (true) {
                    // Prompt user for login method
                    System.out.println("\nPlease select an option:");
                    System.out.println("1. Login using username");
                    System.out.println("2. Login using account number");
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
                if (choice == 1) { // Login using username
                    // Attempt login up to 3 times
                    int i;
                    for (i = 0; i < 3; i++) {
                        System.out.println("Input username: ");
                        String username = scan.next();
                        System.out.println("Input password: ");
                        String password = scan.next();
                        if (username.equals(Helper.getUsername()) && password.equals(Helper.getPassword())) {
                            System.out.println("Successful Sign-in");
                            break;
                        } else {
                            System.out.println("Incorrect Username or Password");
                        }
                    }
                    if (i >= 3) {
                        // Exit if login attempts exceed 3
                        System.out.println("Too many unsuccessful login attempts.");
                        System.out.println("Exiting program...");
                        return;
                    }
                } else if (choice == 2) { // Login using account number
                    int i;
                    for (i = 0; i < 3; i++) {
                        int acctNumber = 0;
                        try {
                            System.out.println("Input account number: ");
                            acctNumber = Integer.parseInt(scan.next());
                        } catch (Exception e) {
                            System.out.println("Invalid Input");
                        }
                        System.out.println("Input password: ");
                        String password = scan.next();
                        if (password.equals(Helper.getPassword()) && acctNumber == Helper.getAcctNum()) {
                            System.out.println("Successful Sign-in");
                            break;
                        } else {
                            System.out.println("Incorrect Account Number or Password");
                        }
                    }
                    if (i >= 3) {
                        // Exit if login attempts exceed 3
                        System.out.println("Too many unsuccessful login attempts.");
                        System.out.println("Exiting program...");
                        return;
                    }
                }
            } else if (choice == 3) { // Exit
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
                    while (true) {
                        try {
                            choice = scan.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid Input");
                        }
                    }
                    System.out.println();
                    if (choice < 1 || choice > 3) {
                        System.out.println("Invalid Input");
                    } else {
                        break;
                    }
                }
                if (choice == 1) { // Increment Day
                    day++;
                    Helper.setDay(day);
                } else if (choice == 2) { // Utility Menu Access
                    while (true) {
                        System.out.println("Please select a Utility Account action below:");
                        System.out.println("1: Check Payment History");
                        System.out.println("2: Pay Upcoming Payment");
                        System.out.println("3: Exit");

                        // Scanning menu input select
                        while (true) {
                            while (true) {
                                try {
                                    subChoice = scan.nextInt();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid Input");
                                }
                            }
                            System.out.println();
                            if (subChoice < 1 || subChoice > 3) {
                                System.out.println("Invalid Input");
                            } else {
                                break;
                            }
                        }

                        if (subChoice == 1) { // Payment history
                            Helper.paymentHistory();
                        } else if (subChoice == 2) { // Pay Payment
                            int unpaidCount = 0;
                            int input = 0;
                            double amountDue = 0.0;
                            double balance = 0.0;
                            String option = "";
                            unpaidCount = Helper.unpaidBills();
                            if (unpaidCount == 0) {
                                System.out.println("You have no bills to pay! Congrats!\n");
                            } else {
                                System.out.println("Enter the due date of the bill you'd like to pay or press 0 to exit.");
                                while (true) {
                                    try {
                                        input = Integer.parseInt(scan.next());
                                        if (input < 0) {
                                            System.out.println("Invalid Input. Please enter a valid number.");
                                        } else {
                                            break;
                                        }

                                    } catch (Exception e) {
                                        System.out.println("Invalid Input");
                                    }
                                }

                                if (input == 0) {
                                    System.out.println("Exiting...");
                                    break;
                                } else {
                                    amountDue = Helper.findBillByDueDate(input);

                                    if (amountDue == 0) {
                                        System.out.println("Exiting...");
                                        break;
                                    } else {
                                        while (true) {
                                            try {
                                                option = scan.next();
                                                if (!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")) {
                                                    System.out.println("Invalid Input. Please enter a valid character.");
                                                } else {
                                                    break;
                                                }


                                            } catch (Exception e) {
                                                System.out.println("Invalid Input");
                                            }
                                        }
                                        if (option.equalsIgnoreCase("N")) {
                                            System.out.println("Going back to bill selection screen.\n");
                                        } else {
                                            balance = Helper.subtractFromCheckingBalance(amountDue);
                                            System.out.println("Your current checking balance: " + balance);
                                            System.out.println("Thank you for paying your bill!\n");
                                            Helper.changeBillStatus(input);
                                        }
                                    }
                                }
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
