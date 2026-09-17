public class SavingsAccount extends Account {
    private long minBalance;

    public SavingsAccount(String num, String name, long bal, long minBalance) {
        super(num, name, bal);
        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() { 
        return 4.0; 
    }

    @Override
    public boolean canWithdraw(long amount) {
        return (getBalance() - amount) >= minBalance;
    }
}
