import java.time.LocalDate;

public class MiniBank {
    public static void main(String[] args) {
        // From previous labs
        System.out.println(Validator.isValidMobile("9876543210"));
        System.out.println(Validator.isValidMobile("12345"));
        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println(cmd.type() + " " + cmd.accountNumber() + " " + cmd.amount());

        System.out.println("\n--- Practical 5 Tests ---");
        Account[] accounts = {
            new SavingsAccount("SA001", "Alice", 5000, 1000),
            new CurrentAccount("CA001", "Bob", 2000, 5000),
            new FixedDepositAccount("FD001", "Charlie", 10000, LocalDate.now().plusYears(1))
        };

        for (Account acc : accounts) {
            System.out.println(acc.getAccountNumber() + " | " + acc.getOwnerName() + " | " + acc.getClass().getSimpleName());
            System.out.println("Interest Rate: " + acc.interestRate() + "%");
            System.out.println("Monthly Interest: " + acc.monthlyInterest());

            if (acc instanceof SavingsAccount sa) {
                System.out.println("Is SavingsAccount. Can withdraw 4500? " + sa.canWithdraw(4500));
            } else if (acc instanceof CurrentAccount ca) {
                System.out.println("Is CurrentAccount. Can withdraw 5000? " + ca.canWithdraw(5000));
            } else if (acc instanceof FixedDepositAccount fda) {
                System.out.println("Is FixedDepositAccount. Can withdraw 1000? " + fda.canWithdraw(1000));
            }
            System.out.println();
        }

        System.out.println("--- Practical 7 Tests ---");
        Account badAccount = new SavingsAccount("SA002", "Dave", -500, 1000);
        String[] errors = AnnotationValidator.validate(badAccount);
        if (errors.length == 0) {
            System.out.println("No validation errors.");
        } else {
            System.out.println("Validation errors found:");
            for (String err : errors) {
                System.out.println("- " + err);
            }
        }        System.out.println("\n--- Practical 8 Tests ---");
        Account accA = accounts[0];
        Account accB = accounts[1];
        try {
            accA.withdraw(50000);
        } catch (exception.InsufficientFundsException e) {
            System.out.println("Withdrawal failed: " + e.getMessage() + " Shortfall: " + e.getShortfall());
        } catch (exception.BankException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("First withdraw attempt completed.");
        }

        try {
            accA.transfer(accB, 2000);
            System.out.println("Transfer successful!");
        } catch (exception.BankException e) {
            System.out.println("Transfer aborted.");
        }

        class TempResource implements AutoCloseable {
            public TempResource() { System.out.println("TempResource opened."); }
            @Override public void close() { System.out.println("TempResource closed."); }
        }

        try (TempResource tr = new TempResource()) {
            System.out.println("Inside try-with-resources block.");
            accB.deposit(-10);
        } catch (exception.BankException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
