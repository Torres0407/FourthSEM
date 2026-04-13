import java.util.Scanner;

public class HangmanCMD {
    public static void main(String[] args) {
        String word = "KUBERNETES";
        String hidden = "__________";
        int tries = 6;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Cloud Hangman!");

        while (tries > 0 && hidden.contains("_")) {
            System.out.println("\nWord: " + hidden);
            System.out.println("Tries left: " + tries);
            System.out.print("Guess a letter: ");
            char guess = scanner.next().toUpperCase().charAt(0);

            if (word.indexOf(guess) >= 0) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        hidden = hidden.substring(0, i) + guess + hidden.substring(i + 1);
                    }
                }
            } else {
                tries--;
                System.out.println("Wrong!");
            }
        }

        if (hidden.equals(word)) {
            System.out.println("\nCongratulations! You won! The word was: " + word);
        } else {
            System.out.println("\nGame Over! The word was: " + word);
        }
    }
}