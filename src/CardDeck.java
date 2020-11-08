import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

/**
 * Object to create a card deck which the players can use for
 * drawing and discarding cards.
 *
 * A card deck needs to be a queue of cards as cards need to be discarded
 * at the bottom of it and drawn from the top.
 */
public class CardDeck implements CardDeckInterface {

    private Queue<Card> deck;
    private BufferedWriter writer;  // Writer attribute for writing to file.

    /**
     * CardDeck constructor.
     *
     * @param deck - Queue of cards.
     * @param fileName - File name to where the writer needs to write to.
     * @throws IOException - when writing to file is not possible.
     */
    public CardDeck(Queue<Card> deck, String fileName) throws IOException {
        this.deck = deck;
        this.writer = new BufferedWriter(new FileWriter(fileName));
    }

    /**
     * Getter method.
     *
     * @return deck - The current queue of cards in the deck.
     */
    public Queue<Card> getDeck() {
        return deck;
    }

    @Override
    public String toString() {
        return "Deck: " + deck;
    }

    /**
     * Add a given card to the bottom of the current deck.
     *
     * @param card - to be added to bottom.
     */
    @Override
    public void addCardToBottom(Card card) {
        deck.add(card);
    }

    /**
     * Remove and return the current card from the top of the deck.
     *
     * @return Card - that was removed.
     */
    @Override
    public Card removeCardFromTop() {
        return deck.remove();
    }

    /**
     * Get the current card on the top of the deck.
     *
     * @return Card - that is on top.
     */
    @Override
    public Card getTopCard() {
        return deck.peek();
    }

    /**
     * Method to write to file.
     * Adds given text on a new line of the file.
     *
     * @param message - String with text needed to be added to file.
     * @throws IOException - when writing to file is not possible.
     */
    protected void writeToFile(String message) throws IOException {
        this.writer.write(message);
        this.writer.newLine();
        this.writer.flush();
    }
}
