import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

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
