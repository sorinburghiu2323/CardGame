<<<<<<< HEAD
/**
 *The Card class is used within the decks for players to draw
 * from, as well as be used in the Player class to be held
 * as their hand.
 */

public class Card {
    private int cardNumber;
=======
public class Card{
    private final int cardNumber;
>>>>>>> 77f0ff54815207040d30fac9fe6e3cba6d83c995

    public Card(int cardNumber){
        this.cardNumber = cardNumber;
    }

    /**
     * @return
     * Getter of card number
     */
    public int getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return Integer.toString(cardNumber);
    }
}
