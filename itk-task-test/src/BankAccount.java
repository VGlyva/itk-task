import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final int accountId;
    private int balance;
    private final Lock lock = new ReentrantLock();


    public BankAccount(int accountId, int initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public void deposit(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Deposit amount must be positive");
        balance += amount;
        System.out.println("Account " + accountId + " deposited " + amount + ", new balance: " + balance);
    }

    public boolean withdraw(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount must be positive");
        if (balance < amount) {
            System.out.println("Account " + accountId + " withdrawal of " + amount + " failed: insufficient funds, balance: " + balance);
            return false;
        }
        balance -= amount;
        System.out.println("Account " + accountId + " withdrew " + amount + ", new balance: " + balance);
        return true;
    }

    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
