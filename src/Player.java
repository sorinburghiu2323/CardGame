import java.util.Arrays;
import java.util.LinkedList;

public class Player implements PlayerInterface {
    private Card[] hand = new Card[4];

    public Player(Card[] hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + Arrays.toString(hand) +
                '}';
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    @Override
    public Card drawCard(CardDeck cardDeck) {
        return null;
    }

    @Override
    public void removeCard(CardDeck cardDeck, Card card) {

    }

    @Override
    public Boolean isWin() {
        return null;
    }
}
