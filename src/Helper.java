import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Helper {

    // File names for utility and banking data
    private static final String UTILITIES_FILE = "utilities.json";
    private static final String BANKING_FILE = "banking.json";

    // Retrieve username from the utilities file
    public static String getUsername() {
        String username = "";
        try {
            // Read the utilities file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            // Get the username from the JSON object
            username = (String) jsonObject.get("username");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    // Retrieve password from the utilities file
    public static String getPassword() {
        String password = "";
        try {
            // Read the utilities file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            // Get the password from the JSON object
            password = (String) jsonObject.get("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    // Retrieve account number from the utilities file
    public static int getAcctNum() {
        int acctNum = 0;
        try {
            // Read the utilities file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            // Get the account number from the JSON object
            Long acctNumLong = (Long) jsonObject.get("acctNum");
            acctNum = acctNumLong.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acctNum;
    }

    // Retrieve payment history from the utilities file
    public static void paymentHistory() {
        try {
            // Read the utilities file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Get the array of bills from the JSON object
            JSONArray bills = (JSONArray) jsonObject.get("bills");

            System.out.println("Last Three Paid Bills:");
            int paidCount = 0;
            // Iterate over the bills array to find paid bills
            for (int i = bills.size() - 1; i >= 0; i--) {
                JSONObject bill = (JSONObject) bills.get(i);
                boolean status = (boolean) bill.get("status");
                if (status) {
                    // Print information of paid bills
                    int amount = ((Long) bill.get("amount")).intValue();
                    int dueDate = ((Long) bill.get("dueDate")).intValue();
                    System.out.println("Amount: $" + amount + ", Due Date: Day " + dueDate);
                    paidCount++;
                }
                if (paidCount == 3) {
                    break;
                }
            }
            System.out.println("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Count unpaid bills from the utilities file
    public static int unpaidBills() {
        int unpaidCount = 0;
        try {
            // Read the utilities file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Get the array of bills from the JSON object
            JSONArray bills = (JSONArray) jsonObject.get("bills");

            System.out.println("Next Unpaid Bills (up to 3):");
            // Iterate over the bills array to find unpaid bills
            for (int i = 0; i <= bills.size() - 1; i++) {
                JSONObject bill = (JSONObject) bills.get(i);
                boolean status = (boolean) bill.get("status");
                if (!status) {
                    // Print information of unpaid bills
                    int amount = ((Long) bill.get("amount")).intValue();
                    int dueDate = ((Long) bill.get("dueDate")).intValue();
                    System.out.println("Amount: $" + amount + ", Due Date: Day " + dueDate);
                    unpaidCount++;
                }
                if (unpaidCount == 3) {
                    break;
                }
            }
            System.out.println("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unpaidCount;
    }

    // Retrieve current day from the utilities file
    public static int getDay() {
        int day = 0;
        try {
            // Read the utilities file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            // Get the day from the JSON object
            Long dayLong = (Long) jsonObject.get("days");
            day = dayLong.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    // Update current day in the utilities file
    public static void setDay(int day) {
        try {
            // Read the utilities file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Update the day in the JSON object
            jsonObject.put("days", day);

            // Write the updated JSON object back to the file
            try (FileWriter file = new FileWriter(UTILITIES_FILE)) {
                file.write(formatJson(jsonObject));
                file.flush();
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Find the amount of a bill by its due date
    public static double findBillByDueDate(int dueDate) {
        double amount = 0.0;
        try {
            // Read the utilities file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Get the array of bills from the JSON object
            JSONArray bills = (JSONArray) jsonObject.get("bills");

            System.out.println("Bills for Due Date " + dueDate + ":");

            boolean found = false;
            // Iterate over the bills array to find the bill with the given due date
            for (Object obj : bills) {
                JSONObject bill = (JSONObject) obj;
                int billDueDate = ((Long) bill.get("dueDate")).intValue();
                if (billDueDate == dueDate) {
                    found = true;
                    // Retrieve the amount of the bill, handling different types (Long or Double)
                    Object amountObj = bill.get("amount");
                    if (amountObj instanceof Double) {
                        amount = (Double) amountObj;
                    } else if (amountObj instanceof Long) {
                        amount = ((Long) amountObj).doubleValue();
                    } else {
                        // Handle the case where amount is not a Double or Long
                        // You might want to throw an exception, log an error, or handle it in some other way
                    }
                    // Print information of the found bill
                    System.out.println("Amount: $" + amount + ", Status: " + bill.get("status"));
                    System.out.println("Your total due is: $" + amount + "\n");
                    System.out.println("Would you like to pay this bill now? Y/N\n");
                }
            }

            if (!found) {
                System.out.println("No bills found for due date " + dueDate);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }

    // Change the status of a bill to paid
    public static void changeBillStatus(int dueDate) {
        try {
            // Read the utilities file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Get the array of bills from the JSON object
            JSONArray bills = (JSONArray) jsonObject.get("bills");

            // Iterate over the bills array to find the bill with the given due date
            for (Object obj : bills) {
                JSONObject bill = (JSONObject) obj;
                int billDueDate = ((Long) bill.get("dueDate")).intValue();
                if (billDueDate == dueDate) {
                    bill.put("status", true); // Change status to true (paid)
                    break;
                }
            }

            // Write the updated JSON object back to the file
            FileWriter writer = new FileWriter(filePath + "/" + UTILITIES_FILE);
            writer.write(jsonObject.toJSONString());
            writer.close();

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Subtract an amount from the checking account balance
    public static double subtractFromCheckingBalance(double amount) {
        double updatedBalance = 0.0; // Default value to indicate failure
        try {
            // Read the banking file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + BANKING_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Get the array of accounts from the JSON object
            JSONArray accounts = (JSONArray) jsonObject.get("accounts");

            // Find the checking account
            for (Object obj : accounts) {
                JSONObject account = (JSONObject) obj;
                String accountId = (String) account.get("accountId");
                if (accountId.equals("CHK123")) {
                    // Get the current balance
                    double balance = (double) account.get("balance");

                    // Check if subtracting the amount would make the balance negative
                    if (balance - amount < 0) {
                        System.out.println("Insufficient funds. Cannot perform transaction.");
                        return balance; // Exit the method and indicate failure
                    }
                    // Subtract the amount from the balance
                    balance -= amount;
                    // Update the balance in the JSON object
                    account.put("balance", balance);
                    updatedBalance = balance; // Update the updatedBalance variable
                    break; // No need to continue iterating
                }
            }

            // Write the updated JSON object back to the file
            FileWriter writer = new FileWriter(filePath + "/" + BANKING_FILE);
            writer.write(jsonObject.toJSONString());
            writer.close();

            reader.close();
            System.out.println("Subtracted $" + amount + " from the checking balance.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedBalance; // Return the updated balance
    }

    // Set account details in the utilities file
    public static void setAccount(String username, String password, int acctNum) {
        try {
            // Read the utilities file
            String filePath = new File("").getAbsolutePath();
            FileReader reader = new FileReader(filePath + "/" + UTILITIES_FILE);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Update account details in the JSON object
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("acctNum", acctNum);

            // Write the updated JSON object back to the file
            try (FileWriter file = new FileWriter(UTILITIES_FILE)) {
                file.write(formatJson(jsonObject));
                file.flush();
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to format JSON object with indentation
    public static String formatJson(JSONObject jsonObject) {
        return jsonObject.toJSONString().replace(",", ",\n")
                .replace("{", "{\n").replace("}", "\n}").replace("[", "[\n").replace("]", "\n]");
    }
}
