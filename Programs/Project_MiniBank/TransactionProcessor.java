import java.util.concurrent.*;

public class TransactionProcessor {
    private ExecutorService ex = Executors.newFixedThreadPool(4);
    private ConcurrentHashMap<String, Account> map = new ConcurrentHashMap<>();

    public void addAccount(Account a) {
        map.put(a.getAccountNumber(), a);
    }

    public Account getAccount(String num) {
        return map.get(num);
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return map;
    }

    public void submit(Runnable r) {
        ex.execute(r);
    }

    public void stop() {
        ex.shutdown();
        try {
            if (!ex.awaitTermination(5, TimeUnit.SECONDS)) {
                ex.shutdownNow();
            }
        } catch (InterruptedException e) {
            ex.shutdownNow();
        }
    }
}
