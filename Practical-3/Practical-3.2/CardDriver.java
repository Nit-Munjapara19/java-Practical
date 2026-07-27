public class CardDriver {
    public static void main(String[] args) {

        Card[] cards = new Card[5];

        Card[] input = {
            new Card("Ace", "Spades"),
            new Card("King", "Hearts"),
            new Card("Queen", "Clubs"),
            new Card("Ace", "Spades"),   // Duplicate
            new Card("10", "Diamonds")
        };

        int count = 0;
        boolean duplicateFound = false;

        // Add cards one by one and check for duplicates
        for (Card card : input) {
            boolean found = false;

            for (int i = 0; i < count; i++) {
                if (card.equals(cards[i])) {
                    System.out.println("Duplicate found: " + card);
                    found = true;
                    duplicateFound = true;
                    break;
                }
            }

            if (!found) {
                cards[count] = card;
                count++;
            }

            // Stop after finding the first duplicate
            if (duplicateFound) {
                break;
            }
        }
    }
}