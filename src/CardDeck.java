import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

/**
 * CardDeck will be a queue to simulate the drawing and discarding
 * done by the players, since the discarding is done by placing
 * the card at the bottom (end of queue). Then we will take the
 * card from the front of the queue.
 */
public class CardDeck implements CardDeckInterface {

    private Queue<Card> deck;
    private BufferedWriter writer;

    public CardDeck(Queue<Card> deck, String fileName) throws IOException {
        this.deck = deck;
        this.writer = new BufferedWriter(new FileWriter(fileName));
    }

    public Queue<Card> getDeck() {
        return deck;
    }

    @Override
    public String toString() {
        return "Deck: " + deck;
    }

    @Override
    public void addCardToBottom(Card card) {
        deck.add(card);
    }

    @Override
    public Card removeCardFromTop() {
        return deck.remove();
    }

    @Override
    public Card getTopCard() {
        return deck.peek();
    }

    protected void writeToFile(String message) throws IOException {
        this.writer.write(message);
        this.writer.newLine();
        this.writer.flush();
    }
}
