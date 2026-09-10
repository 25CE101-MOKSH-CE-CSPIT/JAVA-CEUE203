public class AccountWorker implements Runnable {
    private Account acc;
    private int tm;
    private long amt;

    public AccountWorker(Account acc, int tm, long amt) {
        this.acc = acc;
        this.tm = tm;
        this.amt = amt;
    }

    @Override
    public void run() {
        for (int i = 0; i < tm; i++) {
            try {
                acc.deposit(amt);
            } catch (exception.InvalidAmountException e) {
            }
        }
    }
}
