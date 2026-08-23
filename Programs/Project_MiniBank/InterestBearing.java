public interface InterestBearing {
    double interestRate();
    long getBalance();

    default double yearlyInterest() {
        return getBalance() * (interestRate() / 100);
    }

    default double monthlyInterest() {
        return yearlyInterest() / 12;
    }
}
