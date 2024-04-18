public class SavingsAccount extends Account {
    private static final double DAILY_TRANSFER_LIMIT = 100.0;
    private double dailyTransferred = 0.0;

    public SavingsAccount(String accountId, double initialBalance) {
        super(accountId, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        // Withdrawals not allowed in Savings Account
        return false;
    }

    @Override
    public boolean transfer(Account toAccount, double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds in savings account.");
        }
        if (dailyTransferred + amount > DAILY_TRANSFER_LIMIT) {
            throw new IllegalArgumentException("Transfer limit exceeded. Maximum transfer allowed from savings is $100 per day.");
        }
        if (toAccount instanceof CheckingAccount) {
            ((CheckingAccount) toAccount).transferFromSavings(amount); // Use specialized method for transfers.
        } else {
            toAccount.deposit(amount); // Default deposit for other account types if any.
        }
        balance -= amount;
        dailyTransferred += amount;
        return true;
    }

    public void resetDailyLimits() {
        dailyTransferred = 0.0;
    }

    public double getDailyTransferred() {
        return dailyTransferred;
    }

    public void setDailyTransferred(double dailyTransferred) {
        this.dailyTransferred = dailyTransferred;
    }

}
