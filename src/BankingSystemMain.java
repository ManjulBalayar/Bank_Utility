import java.util.Scanner;

public class BankingSystemMain {
    private static BankManager bankManager = new BankManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        JsonManager jsonManager = new JsonManager();
        Account[] accounts = jsonManager.readAccounts();
        bankManager.setAccounts(accounts);
        int currentDay = jsonManager.getCurrentDay(); // Retrieve the current day from JSON

        System.out.println("Welcome to The ATM Machine and the Utility Company Banking System");
        System.out.println("Today is Day: " + currentDay);
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Deposit to Checking Account");
            System.out.println("2. Deposit to Savings Account");
            System.out.println("3. Withdraw from Checking Account");
            System.out.println("4. Transfer from Checking to Savings");
            System.out.println("5. Transfer from Savings to Checking");
            System.out.println("6. Check Balances");
            System.out.println("7. Check Daily Transaction Limits");
            System.out.println("8. Check Your Bank Account Numbers");
            System.out.println("9. Move to Next Day");
            System.out.println("10. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleDeposit();
                    break;
                case 2:
                    handleSavingsDeposit();
                    break;
                case 3:
                    handleWithdrawal();
                    break;
                case 4:
                    handleTransfer();
                    break;
                case 5:
                    handleTransferFromSavingsToChecking();
                    break;
                case 6:
                    displayBalances();
                    break;
                case 7:
                    displayDailyLimits();
                    break;
                case 8:
                    displayAccountNumbers();
                    break;
                case 9:
                    currentDay++;
                    System.out.println("Moving to Day: " + currentDay);
                    bankManager.endOfDayProcessing();  // Reset limits
                    break;
                case 10:
                    System.out.println("Resetting daily limits and exiting...");
                    bankManager.endOfDayProcessing();  // Reset limits
                    jsonManager.writeAccountsAndDay(bankManager.getAccounts(), currentDay);  // Save current day
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayAccountNumbers() {
        System.out.println("Checking Account Number: " + bankManager.getCheckingAccount().getAccountId());
        System.out.println("Savings Account Number: " + bankManager.getSavingsAccount().getAccountId());
    }

    private static void handleTransferFromSavingsToChecking() {
        System.out.println("Enter amount to transfer from Savings to Checking:");
        double amount = scanner.nextDouble();
        try {
            if (bankManager.transferFromSavingsToChecking(amount)) {
                System.out.println("Transfer successful.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleDeposit() {
        System.out.println("Enter amount to deposit into Checking Account:");
        double amount = scanner.nextDouble();
        bankManager.depositToChecking(amount);
    }

    private static void handleSavingsDeposit() {
        System.out.println("Enter amount to deposit into Savings Account:");
        double amount = scanner.nextDouble();
        bankManager.depositToSaving(amount);
    }

    private static void handleWithdrawal() {
        System.out.println("Enter amount to withdraw from Checking Account:");
        double amount = scanner.nextDouble();
        if (!bankManager.withdrawFromChecking(amount)) {
            System.out.println("Withdrawal failed. Check balance or limits.");
        }
    }

    private static void handleTransfer() {
        System.out.println("Enter amount to transfer from Checking to Savings:");
        double amount = scanner.nextDouble();
        if (!bankManager.transferToSavings(amount)) {
            System.out.println("Transfer failed. Check balance or limits.");
        }
    }

    private static void displayBalances() {
        System.out.println("Checking Account Balance: $" + bankManager.getCheckingAccountBalance());
        System.out.println("Savings Account Balance: $" + bankManager.getSavingsAccountBalance());
    }

    private static void displayDailyLimits() {
        System.out.println("Your daily limits are: $5000 deposit for checkings and savings per day, $500 withdraw from checking, $100 transfer from savings to checking!");
        System.out.println("Checking Account Daily Deposited: $" + bankManager.getCheckingDailyDeposited());
        System.out.println("Checking Account Daily Withdrawn: $" + bankManager.getCheckingDailyWithdrawn());
        System.out.println("Savings Account Daily Deposited: $" + bankManager.getSavingDailyDeposited());
        System.out.println("Savings Account Daily Transferred: $" + bankManager.getSavingsDailyTransferred());
    }
}
