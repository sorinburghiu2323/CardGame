import java.util.Queue;

public class CardDeck implements CardDeckInterface {

    private Queue<Card> deck;

    public CardDeck(Queue<Card> deck) {
        this.deck = deck;
    }

    public void addCardToBottom(Card card) {
//        System.out.println("Add " + deck);
        deck.add(card);
    }

    public String toString() {
        return "Deck: " + deck;
    }

    public Card removeCardFromTop() {
//        System.out.println("Remove " + deck);
        return deck.remove();
    }
}
