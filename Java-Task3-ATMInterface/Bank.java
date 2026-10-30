import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts;

    // Constructor
    public Bank() {
        accounts = new HashMap<>();

        // Create sample accounts
        accounts.put("1001",
                new Account("1001", "Aman", 1234, 5000));

        accounts.put("1002",
                new Account("1002", "Rahul", 5678, 8000));

        accounts.put("1003",
                new Account("1003", "Priya", 4321, 10000));
    }

    // Find account
    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    // Transfer money
    public boolean transfer(String senderNumber,
            String receiverNumber,
            double amount) {

        Account sender = accounts.get(senderNumber);
        Account receiver = accounts.get(receiverNumber);

        if (sender == null || receiver == null) {
            return false;
        }

        if (senderNumber.equals(receiverNumber)) {
            return false;
        }

        if (amount <= 0 || amount > sender.getBalance()) {
            return false;
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        return true;
    }
}