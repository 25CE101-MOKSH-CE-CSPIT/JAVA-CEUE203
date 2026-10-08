import exception.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

public class MiniBank {
    public static void main(String[] a) {
        if (a.length > 0 && a[0].equals("test")) {
            BankTest.runAll();
            return;
        }

        BankInfo bi = new BankInfo("MiniBank India", "Charusat Branch");
        System.out.println("=========================================");
        System.out.println(bi.name() + " | " + bi.branch());
        System.out.println("=========================================");

        BankService bs = new BankService();
        Path dat = Paths.get("accounts.dat");
        if (new File("accounts.dat").exists()) {
            try {
                bs.loadState(dat);
                System.out.println("Loaded existing accounts from accounts.dat");
            } catch (Exception e) {
            }
        } else {
            try {
                bs.openAccount(new SavingsAccount("SA001", "Alice", 5000, 1000));
                bs.openAccount(new CurrentAccount("CA001", "Bob", 3000, 2000));
                bs.openAccount(new FixedDepositAccount("FD001", "Charlie", 10000, LocalDate.now().plusYears(1)));
            } catch (Exception e) {
            }
        }

        Scanner sc = new Scanner(System.in);
        MenuOption[] opts = MenuOption.values();
        boolean run = true;

        while (run) {
            System.out.println("\n--- MENU ---");
            for (int i = 0; i < opts.length; i++) {
                System.out.println((i + 1) + ". " + opts[i]);
            }
            System.out.print("Select: ");
            int c = sc.nextInt();
            sc.nextLine();

            if (c < 1 || c > opts.length) {
                System.out.println("Invalid choice.");
                continue;
            }

            MenuOption op = opts[c - 1];
            String opName = op.name();

            if (opName.equals("OPEN_ACCOUNT")) {
                System.out.print("Type (1.Savings 2.Current 3.FD): ");
                int tp = sc.nextInt();
                sc.nextLine();
                System.out.print("Acc No: ");
                String num = sc.nextLine();
                System.out.print("Owner: ");
                String nm = sc.nextLine();
                System.out.print("Balance: ");
                long bal = sc.nextLong();
                sc.nextLine();

                Account acc = null;
                if (tp == 1) {
                    System.out.print("Min Balance: ");
                    long mb = sc.nextLong();
                    acc = new SavingsAccount(num, nm, bal, mb);
                } else if (tp == 2) {
                    System.out.print("Overdraft Limit: ");
                    long od = sc.nextLong();
                    acc = new CurrentAccount(num, nm, bal, od);
                } else if (tp == 3) {
                    acc = new FixedDepositAccount(num, nm, bal, LocalDate.now().plusYears(1));
                }

                if (acc != null) {
                    try {
                        bs.openAccount(acc);
                        System.out.println("Account opened successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            } else if (opName.equals("DEPOSIT")) {
                System.out.print("Acc No: ");
                String num = sc.nextLine();
                System.out.print("Amount: ");
                long amt = sc.nextLong();
                try {
                    bs.deposit(num, amt);
                    System.out.println("Deposited. New balance: " + bs.getAccount(num).getBalance());
                } catch (Exception e) {
                    System.out.println("Failed: " + e.getMessage());
                }
            } else if (opName.equals("WITHDRAW")) {
                System.out.print("Acc No: ");
                String num = sc.nextLine();
                System.out.print("Amount: ");
                long amt = sc.nextLong();
                try {
                    bs.withdraw(num, amt);
                    System.out.println("Withdrawn. New balance: " + bs.getAccount(num).getBalance());
                } catch (InsufficientFundsException e) {
                    System.out.println("Failed: " + e.getMessage() + " Shortfall: " + e.getShortfall());
                } catch (Exception e) {
                    System.out.println("Failed: " + e.getMessage());
                }
            } else if (opName.equals("TRANSFER")) {
                System.out.print("From Acc No: ");
                String from = sc.nextLine();
                System.out.print("To Acc No: ");
                String to = sc.nextLine();
                System.out.print("Amount: ");
                long amt = sc.nextLong();
                try {
                    bs.transfer(from, to, amt);
                    System.out.println("Transfer completed.");
                } catch (Exception e) {
                    System.out.println("Failed: " + e.getMessage());
                }
            } else if (opName.equals("STATEMENT")) {
                System.out.print("Acc No: ");
                String num = sc.nextLine();
                try {
                    System.out.println(bs.statement(num));
                } catch (Exception e) {
                    System.out.println("Failed: " + e.getMessage());
                }
            } else if (opName.equals("LIST_ACCOUNTS")) {
                System.out.println("--- All Accounts ---");
                for (Account ac : bs.listAccounts()) {
                    System.out.println(ac);
                }
            } else if (opName.equals("BATCH_PROCESS")) {
                System.out.println("Running concurrent batch processing...");
                bs.runBatch();
                System.out.println("Batch processing completed.");
            } else if (opName.equals("GENERATE_REPORT")) {
                Path rep = Paths.get("reports", "eod_report.txt");
                try {
                    bs.generateReport(rep);
                    System.out.println("Report generated at " + rep.toAbsolutePath());
                } catch (Exception e) {
                    System.out.println("Report error: " + e.getMessage());
                }
            } else if (opName.equals("EXIT")) {
                try {
                    bs.saveState(dat);
                    System.out.println("State saved to accounts.dat");
                } catch (Exception e) {
                }
                System.out.println("Goodbye!");
                run = false;
            }
        }
        sc.close();
    }
}
