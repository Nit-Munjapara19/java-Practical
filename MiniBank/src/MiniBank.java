import exception.*;
import model.Account;
import repository.Repository;
import service.BankService;
import util.ReportGenerator;

import java.nio.file.*;
import java.util.*;

public class MiniBank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankInfo info = new BankInfo("MiniBank", "Main Branch");
        System.out.println("================================");
        System.out.println(info.name() + " - " + info.branch());
        System.out.println("================================");

        Repository<Account> repo = new Repository<>();
        BankService bank = new BankService(repo,
                Paths.get("data/accounts.dat"),
                Paths.get("data/logs/transactions.log"));

        try { bank.load(); }
        catch (Exception e) { System.out.println("Starting with empty data."); }

        boolean running = true;
        while (running) {
            System.out.println("\n1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Statement");
            System.out.println("6. List Accounts");
            System.out.println("7. Report");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> {
                        System.out.print("Account number: ");
                        String no = sc.nextLine();
                        System.out.print("Owner name: ");
                        String name = sc.nextLine();
                        System.out.print("Initial balance: ");
                        long amount = Long.parseLong(sc.nextLine());
                        System.out.println("Created: " + bank.openAccount(no, name, amount));
                    }
                    case 2 -> {
                        System.out.print("Account number: ");
                        String no = sc.nextLine();
                        System.out.print("Amount: ");
                        bank.deposit(no, Long.parseLong(sc.nextLine()));
                        System.out.println("Deposit successful.");
                    }
                    case 3 -> {
                        System.out.print("Account number: ");
                        String no = sc.nextLine();
                        System.out.print("Amount: ");
                        bank.withdraw(no, Long.parseLong(sc.nextLine()));
                        System.out.println("Withdrawal successful.");
                    }
                    case 4 -> {
                        System.out.print("From: ");
                        String from = sc.nextLine();
                        System.out.print("To: ");
                        String to = sc.nextLine();
                        System.out.print("Amount: ");
                        bank.transfer(from, to, Long.parseLong(sc.nextLine()));
                        System.out.println("Transfer successful.");
                    }
                    case 5 -> {
                        System.out.print("Account number: ");
                        System.out.println(bank.statement(sc.nextLine()));
                    }
                    case 6 -> bank.listAccounts().forEach(System.out::println);
                    case 7 -> {
                        ReportGenerator.generate(Paths.get("data/logs"),
                                Paths.get("report/end-of-day-report.txt"));
                        System.out.println("Report generated.");
                    }
                    case 8 -> {
                        bank.save();
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}