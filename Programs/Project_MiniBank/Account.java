import model.annotation.Id;
import model.annotation.Positive;
import java.util.Objects;
import java.io.Serializable;

public abstract class Account implements Transactable, InterestBearing, Comparable<Account>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private final String accountNumber;
    private String ownerName;

    @Positive
    private long balance;

    public Account(String num, String name, long bal) {
        accountNumber = num;
        ownerName = name;
        balance = bal;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }

    @Override
    public synchronized void deposit(long amount) throws exception.InvalidAmountException {
        if (amount <= 0) throw new exception.InvalidAmountException("Deposit amount must be > 0.");
        balance += amount;
    }

    @Override
    public synchronized boolean withdraw(long amount) throws exception.InsufficientFundsException, exception.InvalidAmountException {
        if (amount <= 0) throw new exception.InvalidAmountException("Withdrawal amount must be > 0.");
        if (!canWithdraw(amount)) {
            long shortfall = amount - getBalance();
            if (shortfall < 0) shortfall = amount;
            throw new exception.InsufficientFundsException("Insufficient funds.", shortfall);
        }
        balance -= amount;
        return true;
    }

    public void transfer(Account to, long amount) throws exception.BankException {
        Account first = accountNumber.compareTo(to.accountNumber) < 0 ? this : to;
        Account second = first == this ? to : this;
        synchronized (first) {
            synchronized (second) {
                System.out.println("Transferring " + amount + " to " + to.getAccountNumber());
                this.withdraw(amount);
                to.deposit(amount);
            }
        }
        System.out.println("Transfer operation finished.");
    }

    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    @Override
    public int compareTo(Account o) {
        return accountNumber.compareTo(o.accountNumber);
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account a = (Account) o;
        return Objects.equals(accountNumber, a.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber);
    }
}
