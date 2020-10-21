import java.util.Queue;

public class CardDeck implements CardDeckInterface {

    private Queue<Card> deck;

    public CardDeck(Queue<Card> deck) {
        this.deck = deck;
    }

    @Override
    public void addCardToBottom(Card card) {

    }

    @Override
    public Card removeCardFromTop(Card card) {
        return null;
    }
}
