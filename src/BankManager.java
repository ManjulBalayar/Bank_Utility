public class BankManager {
    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    public BankManager() {
        // Load from JSON or create new accounts with initial balances and IDs.
        checkingAccount = new CheckingAccount("CHK123", 1000.0);
        savingsAccount = new SavingsAccount("SAV123", 5000.0);
    }

    public void depositToChecking(double amount) {
        try {
            checkingAccount.deposit(amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean withdrawFromChecking(double amount) {
        return checkingAccount.withdraw(amount);
    }

    public boolean transferToSavings(double amount) {
        return checkingAccount.transfer(savingsAccount, amount);
    }

    public boolean transferFromSavingsToChecking(double amount) {
        return savingsAccount.transfer(checkingAccount, amount);
    }

    public double getCheckingAccountBalance() {
        return checkingAccount.getBalance();
    }

    public double getSavingsAccountBalance() {
        return savingsAccount.getBalance();
    }

    public void endOfDayProcessing() {
        checkingAccount.resetDailyLimits();
        savingsAccount.resetDailyLimits();
    }

    public Account[] getAccounts() {
        return new Account[]{checkingAccount, savingsAccount};
    }

    public void setAccounts(Account[] accounts) {
        for (Account acc : accounts) {
            if (acc instanceof CheckingAccount) {
                checkingAccount = (CheckingAccount) acc;
            } else if (acc instanceof SavingsAccount) {
                savingsAccount = (SavingsAccount) acc;
            }
        }
    }
    public double getCheckingDailyDeposited() {
        return checkingAccount.getDailyDeposited();
    }

    public double getCheckingDailyWithdrawn() {
        return checkingAccount.getDailyWithdrawn();
    }

    public double getSavingsDailyTransferred() {
        return savingsAccount.getDailyTransferred();
    }

}
