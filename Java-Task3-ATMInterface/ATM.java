import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;

    // Store transaction history for each account
    private Map<String, List<Transaction>> history;

    // Constructor
    public ATM(Bank bank) {
        this.bank = bank;
        scanner = new Scanner(System.in);
        history = new HashMap<>();
    }

    // Start ATM
    public void start() {

        System.out.println("================================");
        System.out.println("       WELCOME TO ATM");
        System.out.println("================================");

        Account account = login();

        if (account == null) {
            System.out.println("Access denied. Try again later.");
            return;
        }

        System.out.println("\nWelcome, "
                + account.getAccountHolderName() + "!");

        boolean running = true;

        while (running) {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.println("==============================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    checkBalance(account);
                    break;

                case 2:
                    depositMoney(account);
                    break;

                case 3:
                    withdrawMoney(account);
                    break;

                case 4:
                    transferMoney(account);
                    break;

                case 5:
                    showHistory(account);
                    break;

                case 6:
                    System.out.println("Thank you for using our ATM!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Login with maximum 3 attempts
    private Account login() {

        for (int i = 1; i <= 3; i++) {

            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.nextLine().trim();

            Account account = bank.getAccount(accountNumber);

            if (account == null) {
                System.out.println("Account not found.");
                System.out.println("Attempts remaining: " + (3 - i));
                continue;
            }

            int pin = readInt("Enter your PIN: ");

            if (account.verifyPin(pin)) {
                System.out.println("Login successful!");
                return account;
            }

            System.out.println("Incorrect PIN!");
            System.out.println("Attempts remaining: " + (3 - i));
        }

        return null;
    }

    // Check balance
    private void checkBalance(Account account) {

        System.out.println("Your balance is: Rs. "
                + account.getBalance());
    }

    // Deposit money
    private void depositMoney(Account account) {

        double amount = readAmount("Enter deposit amount: ");

        if (account.deposit(amount)) {

            addHistory(account, "DEPOSIT", amount);

            System.out.println("Deposit successful!");
            checkBalance(account);

        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money
    private void withdrawMoney(Account account) {

        double amount = readAmount("Enter withdrawal amount: ");

        if (account.withdraw(amount)) {

            addHistory(account, "WITHDRAWAL", amount);

            System.out.println("Withdrawal successful!");
            checkBalance(account);

        } else {
            System.out.println(
                    "Insufficient balance or invalid amount.");
        }
    }

    // Transfer money
    private void transferMoney(Account account) {

        System.out.print("Enter receiver account number: ");
        String receiverNumber = scanner.nextLine().trim();

        double amount = readAmount("Enter transfer amount: ");

        if (bank.transfer(
                account.getAccountNumber(),
                receiverNumber,
                amount)) {

            addHistory(account, "TRANSFER SENT", amount);

            Account receiver = bank.getAccount(receiverNumber);

            addHistory(receiver, "TRANSFER RECEIVED", amount);

            System.out.println("Transfer successful!");

        } else {
            System.out.println(
                    "Transfer failed. Check account and balance.");
        }
    }

    // Add transaction to history
    private void addHistory(
            Account account, String type, double amount) {

        String number = account.getAccountNumber();

        history.putIfAbsent(number, new ArrayList<>());

        history.get(number).add(
                new Transaction(type, amount));
    }

    // Display transaction history
    private void showHistory(Account account) {

        String number = account.getAccountNumber();

        List<Transaction> transactions = history.get(number);

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions == null || transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }

    // Read integer input safely
    private int readInt(String message) {

        while (true) {

            System.out.print(message);

            try {
                return Integer.parseInt(
                        scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Read money amount safely
    private double readAmount(String message) {

        while (true) {

            System.out.print(message);

            try {
                double amount = Double.parseDouble(
                        scanner.nextLine().trim());

                return amount;

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid amount.");
            }
        }
    }
}