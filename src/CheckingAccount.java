public class CheckingAccount extends Account {
    private static final double DAILY_WITHDRAW_LIMIT = 500.0;
    private static final double DAILY_DEPOSIT_LIMIT = 5000.0;
    private double dailyWithdrawn = 0.0;
    private double dailyDeposited = 0.0;

    public CheckingAccount(String accountId, double initialBalance) {
        super(accountId, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        if (dailyDeposited + amount > DAILY_DEPOSIT_LIMIT) {
            throw new IllegalArgumentException("Deposit limit exceeded");
        }
        balance += amount;
        dailyDeposited += amount;
    }

    public void transferFromSavings(double amount) {
        // This method adjusts balance without affecting dailyDeposited.
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds for transfer.");
        }
        balance += amount; // Increase the balance directly without updating dailyDeposited.
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= balance && (dailyWithdrawn + amount) <= DAILY_WITHDRAW_LIMIT) {
            balance -= amount;
            dailyWithdrawn += amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean transfer(Account toAccount, double amount) {
        if (amount <= balance) {
            balance -= amount;
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }

    public void resetDailyLimits() {
        dailyWithdrawn = 0.0;
        dailyDeposited = 0.0;
    }

    public double getDailyWithdrawn() {
        return dailyWithdrawn;
    }

    public double getDailyDeposited() {
        return dailyDeposited;
    }

    public void setDailyWithdrawn(double dailyWithdrawn) {
        this.dailyWithdrawn = dailyWithdrawn;
    }

    public void setDailyDeposited(double dailyDeposited) {
        this.dailyDeposited = dailyDeposited;
    }

}
