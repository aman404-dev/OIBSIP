public class Main {

    public static void main(String[] args) {

        // Create bank
        Bank bank = new Bank();

        // Create ATM
        ATM atm = new ATM(bank);

        // Start ATM
        atm.start();
    }
}