import java.util.Scanner;

public class VendingMachine {

    // (a) Define enum Coin
    enum Coin {
        ONE, TWO, FIVE, TEN
    }

    public static void main(String[] args) {

        // (b) Snack price and running total
        final int PRICE = 15;
        int total = 0;

        Scanner sc = new Scanner(System.in);

        // (c) Loop until enough money is inserted
        while (total < PRICE) {
            System.out.print("Insert coin (ONE, TWO, FIVE, TEN): ");
            String input = sc.next().toUpperCase();

            Coin coin = Coin.valueOf(input);

            int value = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            total += value;
            System.out.println("Total so far: " + total);
        }

        // (e) Print change
        System.out.println("Paid. Change: " + (total - PRICE));

        sc.close();
    }
}