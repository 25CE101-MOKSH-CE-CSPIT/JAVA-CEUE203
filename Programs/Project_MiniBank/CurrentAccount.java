public class CurrentAccount extends Account {
    private long overdraftLimit;

    public CurrentAccount(String num, String name, long bal, long overdraftLimit) {
        super(num, name, bal);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double interestRate() { 
        return 0.0; 
    }

    @Override
    public boolean canWithdraw(long amount) {
        return (getBalance() - amount) >= -overdraftLimit;
    }
}
