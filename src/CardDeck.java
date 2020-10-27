import java.util.Queue;

public class CardDeck implements CardDeckInterface {

    private Queue<Card> deck;
    private int deckID;

    public CardDeck(int deckID, Queue<Card> deck) {
        this.deckID = deckID;
        this.deck = deck;
    }

    @Override
    public void addCardToBottom(Card card) {

    }

    @Override
    public String toString() {
        return "CardDeck{" +
                "deck=" + deck +
                '}';
    }

    @Override
    public Card removeCardFromTop(Card card) {
        return null;
    }
}
