import exception.*;
import model.annotation.Audit;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private Repository<Account> rep = new Repository<>();
    private Path logPath = Paths.get("logs", "transactions.log");

    private void log(String op, String num, long amt) {
        try {
            TransactionLog.append(logPath, op + " " + num + " " + amt);
        } catch (IOException e) {
        }
    }

    @Audit(action = "OPEN_ACCOUNT")
    public void openAccount(Account a) {
        String[] err = AnnotationValidator.validate(a);
        if (err.length > 0) {
            throw new IllegalArgumentException("Validation failed: " + String.join(", ", err));
        }
        rep.save(a);
    }

    public Account getAccount(String num) throws AccountNotFoundException {
        Account a = rep.findById(num);
        if (a == null) throw new AccountNotFoundException("Account not found: " + num);
        return a;
    }

    @Audit(action = "DEPOSIT")
    public void deposit(String num, long amt) throws AccountNotFoundException, InvalidAmountException {
        Account a = getAccount(num);
        a.deposit(amt);
        log("DEPOSIT", num, amt);
    }

    @Audit(action = "WITHDRAW")
    public void withdraw(String num, long amt) throws AccountNotFoundException, InsufficientFundsException, InvalidAmountException {
        Account a = getAccount(num);
        a.withdraw(amt);
        log("WITHDRAW", num, amt);
    }

    @Audit(action = "TRANSFER")
    public void transfer(String from, String to, long amt) throws BankException {
        Account a1 = getAccount(from);
        Account a2 = getAccount(to);
        a1.transfer(a2, amt);
        log("TRANSFER", from + "->" + to, amt);
    }

    public String statement(String num) throws AccountNotFoundException {
        Account a = getAccount(num);
        return StatementFormatter.buildStatement(a);
    }

    public List<Account> listAccounts() {
        return rep.findAll();
    }

    public void saveState(Path p) throws IOException {
        List<Account> ls = rep.findAll();
        StatePersister.save(ls.toArray(new Account[0]), p);
    }

    public void loadState(Path p) throws IOException, ClassNotFoundException {
        Account[] arr = StatePersister.load(p);
        for (Account a : arr) {
            rep.save(a);
        }
    }

    public void runBatch() {
        TransactionProcessor tp = new TransactionProcessor();
        for (Account a : rep.findAll()) {
            tp.addAccount(a);
        }

        List<Account> ls = rep.findAll();
        if (ls.isEmpty()) return;

        Account target = ls.get(0);
        for (int i = 0; i < 8; i++) {
            tp.submit(() -> {
                try {
                    target.deposit(50);
                } catch (InvalidAmountException e) {
                }
            });
        }
        tp.stop();
    }

    public void generateReport(Path repFile) throws IOException {
        ReportGenerator.generate(Paths.get("logs"), repFile);
    }
}
