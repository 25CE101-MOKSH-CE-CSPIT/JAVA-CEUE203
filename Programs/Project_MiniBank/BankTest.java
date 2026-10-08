import exception.*;
import java.nio.file.Paths;

public class BankTest {
    private static int p = 0;
    private static int f = 0;

    private static void check(String nm, boolean cond) {
        if (cond) {
            System.out.println("PASS: " + nm);
            p++;
        } else {
            System.out.println("FAIL: " + nm);
            f++;
        }
    }

    public static void runAll() {
        System.out.println("=== MiniBank Unit Tests ===");

        SavingsAccount sa = new SavingsAccount("SA101", "Alice", 1000, 500);
        try {
            sa.deposit(500);
            check("Deposit increases balance", sa.getBalance() == 1500);
        } catch (Exception e) {
            check("Deposit increases balance", false);
        }

        boolean ex = false;
        try {
            sa.withdraw(2000);
        } catch (InsufficientFundsException e) {
            ex = true;
        } catch (Exception e) {
        }
        check("Withdraw over limit throws InsufficientFundsException", ex);

        Account acc = new SavingsAccount("SA102", "Bob", 0, 0);
        Thread[] th = new Thread[10];
        for (int i = 0; i < 10; i++) {
            th[i] = new Thread(new AccountWorker(acc, 100, 10));
            th[i].start();
        }
        for (Thread t : th) {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        }
        check("Concurrent deposits preserve correct balance", acc.getBalance() == 10000);

        Repository<Account> rp = new Repository<>();
        rp.save(sa);
        Account fd = rp.findById("SA101");
        check("Repository finds by @Id", fd != null && fd.getAccountNumber().equals("SA101"));

        try {
            StatePersister.save(new Account[]{ sa }, Paths.get("test_acc.dat"));
            Account[] ld = StatePersister.load(Paths.get("test_acc.dat"));
            check("StatePersister saves and loads", ld.length == 1 && ld[0].getAccountNumber().equals("SA101"));
        } catch (Exception e) {
            check("StatePersister saves and loads", false);
        }

        System.out.println("\nResults: " + p + " passed, " + f + " failed\n");
    }
}
