public abstract class Account {
    protected String accountId;
    protected double balance;

    public Account(String accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void deposit(double amount);
    public abstract boolean withdraw(double amount);
    public abstract boolean transfer(Account toAccount, double amount);
}
