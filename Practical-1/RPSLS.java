import java.util.Random;
import java.util.Scanner;

public class RPSLS {

    // (a) Define enum
    enum Move {
        ROCK, PAPER, SCISSORS, LIZARD, SPOCK
    }

    // (b) Method to determine winner
    public static int winner(Move a, Move b) {

        if (a == b) {
            return 0;
        }

        return switch (a) {
            case ROCK ->
                (b == Move.SCISSORS || b == Move.LIZARD) ? 1 : -1;

            case PAPER ->
                (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;

            case SCISSORS ->
                (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;

            case LIZARD ->
                (b == Move.SPOCK || b == Move.PAPER) ? 1 : -1;

            case SPOCK ->
                (b == Move.SCISSORS || b == Move.ROCK) ? 1 : -1;
        };
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int playerScore = 0;
        int computerScore = 0;

        Move[] moves = Move.values();

        // (c) Play 5 rounds
        for (int round = 1; round <= 5; round++) {

            System.out.println("\nRound " + round);
            System.out.print("Enter your move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK): ");

            Move player = Move.valueOf(sc.next().toUpperCase());
            Move computer = moves[random.nextInt(moves.length)];

            int result = winner(player, computer);

            System.out.println("You: " + player);
            System.out.println("Computer: " + computer);

            if (result == 1) {
                System.out.println("You win this round!");
                playerScore++;
            } else if (result == -1) {
                System.out.println("Computer wins this round!");
                computerScore++;
            } else {
                System.out.println("Round is a tie!");
            }
        }

        // (d) Print overall winner
        System.out.println("\nFinal Score");
        System.out.println("You: " + playerScore);
        System.out.println("Computer: " + computerScore);

        if (playerScore > computerScore) {
            System.out.println("You win " + playerScore + "–" + computerScore + "!");
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins " + computerScore + "–" + playerScore + "!");
        } else {
            System.out.println("The game is a tie!");
        }

        sc.close();
    }
}