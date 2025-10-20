import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentBank {
    private final Map<Integer, BankAccount> accounts = new ConcurrentHashMap<>();
    private final AtomicInteger accountIdGenerator = new AtomicInteger(1);

    public BankAccount createAccount(int initialBalance) {
        if (initialBalance < 0) throw new IllegalArgumentException("Balance must be positive");
        int newAccountId = accountIdGenerator.getAndIncrement();
        BankAccount account = new BankAccount(newAccountId, initialBalance);
        accounts.put(newAccountId, account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, int amount) {
        System.out.println("Starting transfer of " + amount + " from account " + from.getAccountId() + " to account " + to.getAccountId());
        if (from == to) return;
        System.out.println("Transfer skipped: source and destination accounts are the same.");
        if (amount <= 0) throw new IllegalArgumentException("Transfer amount must be positive");

        BankAccount firstLock = from.getAccountId() < to.getAccountId() ? from : to;
        BankAccount secondLock = from.getAccountId() < to.getAccountId() ? to : from;

        firstLock.lock();
        secondLock.lock();

        try {
            if (!from.withdraw(amount)) {
                System.out.println("Transfer failed: insufficient funds in account " + from.getAccountId());
                return;
            }
            to.deposit(amount);
            System.out.println("Transferred " + amount + " from account " + from.getAccountId() + " to account " + to.getAccountId());
        } finally {
            secondLock.unlock();
            firstLock.unlock();
        }

        System.out.println("Finished transfer of " + amount + " from account " + from.getAccountId() + " to account " + to.getAccountId());
    }

    public int getTotalBalance() {
        int total = 0;
        for (BankAccount account : accounts.values()) {
            total += account.getBalance();
        }
        return total;
    }

}
