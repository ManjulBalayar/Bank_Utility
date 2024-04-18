import java.util.Scanner;

public class BankingSystemMain {
    private static BankManager bankManager = new BankManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        JsonManager jsonManager = new JsonManager();
        Account[] accounts = jsonManager.readAccounts();
        bankManager.setAccounts(accounts);

        System.out.println("Welcome to The ATM Machine and the Utility Company Banking System");
        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Deposit to Checking Account");
            System.out.println("2. Withdraw from Checking Account");
            System.out.println("3. Transfer from Checking to Savings");
            System.out.println("4. Transfer from Savings to Checking");  // Added option
            System.out.println("5. Check Balances");
            System.out.println("6. Check Daily Transaction Limits");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleDeposit();
                    break;
                case 2:
                    handleWithdrawal();
                    break;
                case 3:
                    handleTransfer();
                    break;
                case 4:
                    handleTransferFromSavingsToChecking();
                    break;
                case 5:
                    displayBalances();
                    break;
                case 6:
                    displayDailyLimits();
                    break;
                case 7:
                    System.out.println("Resetting daily limits and exiting...");
                    bankManager.endOfDayProcessing();  // Ensuring this is correctly named
                    jsonManager.writeAccounts(bankManager.getAccounts());
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
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
        System.out.println("Checking Account Daily Deposited: $" + bankManager.getCheckingDailyDeposited());
        System.out.println("Checking Account Daily Withdrawn: $" + bankManager.getCheckingDailyWithdrawn());
        System.out.println("Savings Account Daily Transferred: $" + bankManager.getSavingsDailyTransferred());
    }
}