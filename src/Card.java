/**
 * Class to create a card for the game.
 * A Card instance must have a positive integer value as attribute.
 */
public class Card{

    private final int cardNumber;

    /**
     * Card initializer.
     *
     * @param cardNumber - positive integer with number of card.
     */
    public Card(int cardNumber){
        this.cardNumber = cardNumber;
    }

    /**
     * Card getter.
     *
     * @return cardNumber - number of the Card.
     */
    public int getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return Integer.toString(cardNumber);
    }
}
