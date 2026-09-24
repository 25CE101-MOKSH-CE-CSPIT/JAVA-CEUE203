import java.util.*;

public class AccountStore {
    private Map<String, Account> map = new HashMap<>();
    private List<String> hist = new ArrayList<>();

    public void add(Account a) {
        map.put(a.getAccountNumber(), a);
    }

    public Account get(String num) {
        return map.get(num);
    }

    public Collection<Account> getAll() {
        return map.values();
    }

    public void logTransaction(String t) {
        hist.add(t);
    }

    public List<String> getHistory() {
        return hist;
    }

    public ArrayList<Account> sortedByBalance() {
        ArrayList<Account> ls = new ArrayList<>(map.values());
        ls.sort(Comparator.comparingLong(Account::getBalance).reversed());
        return ls;
    }
}
