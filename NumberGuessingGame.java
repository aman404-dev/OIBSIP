import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    // Read input from the keyboard
    private static final Scanner scanner = new Scanner(System.in);

    // Generate random numbers
    private static final Random random = new Random();

    // Store the result of each round
    static class RoundResult {
        int roundNumber;
        String difficulty;
        int secretNumber;
        int attemptsUsed;
        boolean won;

        RoundResult(int roundNumber, String difficulty,
                    int secretNumber, int attemptsUsed, boolean won) {
            this.roundNumber = roundNumber;
            this.difficulty = difficulty;
            this.secretNumber = secretNumber;
            this.attemptsUsed = attemptsUsed;
            this.won = won;
        }

        void displayResult() {
            if (won) {
                System.out.println("Round " + roundNumber
                        + " - Guessed in " + attemptsUsed
                        + " attempts (" + difficulty + ").");
            } else {
                System.out.println("Round " + roundNumber
                        + " - Not guessed. Attempts used: "
                        + attemptsUsed + " (" + difficulty + ").");
            }
        }
    }

    // Store difficulty settings
    static class Difficulty {
        String name;
        int minimum;
        int maximum;
        int maximumAttempts;

        Difficulty(String name, int minimum, int maximum,
                   int maximumAttempts) {
            this.name = name;
            this.minimum = minimum;
            this.maximum = maximum;
            this.maximumAttempts = maximumAttempts;
        }
    }

    public static void main(String[] args) {

        List<RoundResult> results = new ArrayList<>();

        int roundNumber = 1;
        boolean playAgain = true;

        System.out.println("==================================");
        System.out.println("       NUMBER GUESSING GAME");
        System.out.println("       OASIS INFOBYTE - JAVA");
        System.out.println("==================================");

        while (playAgain) {

            // Step 1: Choose difficulty
            Difficulty difficulty = chooseDifficulty();

            // Step 2: Generate the secret number
            int secretNumber = random.nextInt(
                    difficulty.maximum - difficulty.minimum + 1)
                    + difficulty.minimum;

            int attemptsUsed = 0;
            boolean won = false;

            System.out.println("\nRound " + roundNumber);
            System.out.println("Difficulty: " + difficulty.name);
            System.out.println("Guess a number between "
                    + difficulty.minimum + " and "
                    + difficulty.maximum + ".");
            System.out.println("Maximum attempts: "
                    + difficulty.maximumAttempts);

            // Step 3: Start guessing
            while (attemptsUsed < difficulty.maximumAttempts) {

                System.out.println("\nAttempts used: " + attemptsUsed
                        + "/" + difficulty.maximumAttempts);

                int guess = readInteger("Enter your guess: ");

                // Validate the guess range
                if (guess < difficulty.minimum
                        || guess > difficulty.maximum) {

                    System.out.println("Invalid guess! Enter a number between "
                            + difficulty.minimum + " and "
                            + difficulty.maximum + ".");

                    // Invalid guesses do not consume an attempt
                    continue;
                }

                // Count a valid guess
                attemptsUsed++;

                // Step 4: Compare the guess with the secret number
                if (guess == secretNumber) {

                    System.out.println("\nCorrect! You guessed the number.");
                    System.out.println("Attempts used: " + attemptsUsed);

                    won = true;
                    break;

                } else if (guess < secretNumber) {

                    System.out.println("Too Low!");

                } else {

                    System.out.println("Too High!");
                }
            }

            // Step 5: Display loss message
            if (!won) {
                System.out.println("\nYou Lost!");
                System.out.println("The correct number was: "
                        + secretNumber);
            }

            // Step 6: Save this round's result
            RoundResult result = new RoundResult(
                    roundNumber,
                    difficulty.name,
                    secretNumber,
                    attemptsUsed,
                    won
            );

            results.add(result);

            // Step 7: Display score tracking
            displayScore(results);

            // Step 8: Ask whether the player wants another round
            playAgain = askToPlayAgain();

            if (playAgain) {
                roundNumber++;
            }
        }

        // Final score
        System.out.println("\n==================================");
        System.out.println("          FINAL SCORE");
        System.out.println("==================================");

        displayScore(results);

        System.out.println("\nThank you for playing!");
        System.out.println("Goodbye!");

        scanner.close();
    }

    // Method to select difficulty
    private static Difficulty chooseDifficulty() {

        System.out.println("\nChoose Difficulty:");
        System.out.println("1. Easy   - Range 1 to 50, 10 attempts");
        System.out.println("2. Medium - Range 1 to 100, 7 attempts");
        System.out.println("3. Hard   - Range 1 to 200, 5 attempts");

        while (true) {

            int choice = readInteger("Enter your choice (1-3): ");

            switch (choice) {

                case 1:
                    return new Difficulty("Easy", 1, 50, 10);

                case 2:
                    return new Difficulty("Medium", 1, 100, 7);

                case 3:
                    return new Difficulty("Hard", 1, 200, 5);

                default:
                    System.out.println(
                            "Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }

    // Method to safely read a whole number
    private static int readInteger(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input! Please enter a whole number.");
            }
        }
    }

    // Method to display the score across all rounds
    private static void displayScore(List<RoundResult> results) {

        int wins = 0;
        int losses = 0;

        System.out.println("\n========== SCORE SUMMARY ==========");

        for (RoundResult result : results) {

            result.displayResult();

            if (result.won) {
                wins++;
            } else {
                losses++;
            }
        }

        System.out.println("-----------------------------------");
        System.out.println("Total rounds played: " + results.size());
        System.out.println("Rounds won: " + wins);
        System.out.println("Rounds lost: " + losses);
        System.out.println("===================================");
    }

    // Method to ask whether to play again
    private static boolean askToPlayAgain() {

        while (true) {

            System.out.print("\nPlay Again? (yes/no): ");

            String answer = scanner.nextLine().trim()
                    .toLowerCase();

            if (answer.equals("yes") || answer.equals("y")) {
                return true;
            }

            if (answer.equals("no") || answer.equals("n")) {
                return false;
            }

            System.out.println(
                    "Invalid response! Please type yes or no.");
        }
    }
}