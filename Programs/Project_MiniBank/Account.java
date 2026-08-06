public class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    public Account(String num, String name, long bal) { accountNumber = num; ownerName = name; balance = bal; }
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
}
