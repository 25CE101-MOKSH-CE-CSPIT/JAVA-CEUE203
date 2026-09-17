import java.time.LocalDate;

public class FixedDepositAccount extends Account {
    private LocalDate maturityDate;

    public FixedDepositAccount(String num, String name, long bal, LocalDate maturityDate) {
        super(num, name, bal);
        this.maturityDate = maturityDate;
    }

    @Override
    public double interestRate() { 
        return 7.0; 
    }

    @Override
    public boolean canWithdraw(long amount) {
        return LocalDate.now().isAfter(maturityDate) || LocalDate.now().isEqual(maturityDate);
    }
}
