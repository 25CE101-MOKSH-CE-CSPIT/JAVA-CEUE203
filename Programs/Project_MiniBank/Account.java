import model.annotation.Id;
import model.annotation.Positive;
import java.util.Objects;

public abstract class Account {
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

        public void deposit(long amount) throws exception.InvalidAmountException {
        if (amount <= 0) {
            throw new exception.InvalidAmountException("Deposit amount must be > 0.");
        }
        balance += amount;
    }

    public void withdraw(long amount) throws exception.InsufficientFundsException, exception.InvalidAmountException {
        if (amount <= 0) {
            throw new exception.InvalidAmountException("Withdrawal amount must be > 0.");
        }
        if (!canWithdraw(amount)) {
            long shortfall = amount - getBalance();
            if (shortfall < 0) shortfall = amount;
            throw new exception.InsufficientFundsException("Insufficient funds.", shortfall);
        }
        balance -= amount;
    }

    public void transfer(Account to, long amount) throws exception.BankException {
        try {
            System.out.println("Transferring " + amount + " to " + to.getAccountNumber());
            this.withdraw(amount);
            to.deposit(amount);
        } catch (exception.BankException e) {
            System.out.println("Transfer failed: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Transfer operation finished.");
        }
    }

    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    public double monthlyInterest() {
        return getBalance() * (interestRate() / 100) / 12;
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber);
    }
}

