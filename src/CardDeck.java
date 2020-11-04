import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

public class CardDeck implements CardDeckInterface {

    private Queue<Card> deck;
    private BufferedWriter writer;

    public Queue<Card> getDeck() {
        return deck;
    }

    public CardDeck(Queue<Card> deck, String fileName) throws IOException {
        this.deck = deck;
        this.writer = new BufferedWriter(new FileWriter(fileName));
    }

    public void addCardToBottom(Card card) {
        deck.add(card);
    }

    public String toString() {
        return "Deck: " + deck;
    }

    public Card removeCardFromTop() {
        return deck.remove();
    }

    public Card getTopCard() {
        return deck.peek();
    }

    protected void writeToFile(String message) throws IOException {
        this.writer.write(message);
        this.writer.newLine();
        this.writer.flush();
    }
}
