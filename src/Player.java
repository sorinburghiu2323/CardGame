import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * Player class to simulate a player of the card game.
 * The players are threaded to take their turns by treating each player
 * method as an atomic action.
 */
public class Player extends Thread implements PlayerInterface {

    private final int playerID;  // Unique player number.
    private Card[] hand;
    private CardDeck discardDeck;  // Deck to which the player should discard cards to.
    private CardDeck drawDeck;  // Deck from which the player should draw cards from.
    private BufferedWriter writer;  // Writer attribute for writing to file.
    private volatile static boolean done = false;
    private static int winner;

    /**
     * Player constructor.
     *
     * @param playerID - int of id of player instance.
     * @param hand - array of cards.
     * @param fileName - name of file to write to.
     * @throws IOException - when writing to file is not possible.
     */
    public Player(int playerID, Card[] hand, String fileName) throws IOException {
        this.playerID = playerID;
        this.hand = hand;
        this.writer = new BufferedWriter(new FileWriter(fileName));
    }

    /**
     * Setter for discard deck.
     *
     * @param discardDeck - CardDeck to discard cards to.
     */
    public void setDiscardDeck(CardDeck discardDeck) {
        this.discardDeck = discardDeck;
    }

    /**
     * Setter for draw deck.
     *
     * @param drawDeck - CardDeck to draw cards from.
     */
    public void setDrawDeck(CardDeck drawDeck) {
        this.drawDeck = drawDeck;
    }

    /**
     * Getter for player id.
     *
     * @return playerID - int.
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Getter for player's hand.
     *
     * @return hand - Card array for hand.
     */
    public Card[] getHand() {
        return hand;
    }

    /**
     * Getter for the player's discard deck.
     *
     * @return discardDeck - CardDeck to which the player discards cards to.
     */
    public CardDeck getDiscardDeck() {
        return discardDeck;
    }

    /**
     * Getter for the winner id.
     *
     * @return winner - int id of winner; none if no winner yet.
     */
    public int getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID=" + playerID +
                ", hand=" + Arrays.toString(hand) +
                ", discard=" + discardDeck +
                ", draw=" + drawDeck +
                '}';
    }

    /**
     * Method to draw card from the respective player's draw deck.
     *
     * @return Card - drawn.
     */
    @Override
    public Card drawCard() {
        return drawDeck.removeCardFromTop();
    }

    /**
     * Remove respective card from player's hand.
     *
     * @param card - to be removed.
     */
    @Override
    public void removeCard(Card card) {
        discardDeck.addCardToBottom(card);
    }

    /**
     * Analyse player's hand to see is it is a winning hand.
     * A winning hand must have 4 Cards with the same card number.
     *
     * @return boolean - True if winning hand; otherwise False.
     */
    @Override
    public synchronized boolean isWin() {

        for (int i=0; i<3; i++) {
            if (hand[i].getCardNumber() != hand[i+1].getCardNumber()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to combine the other method to create the effect of a "turn".
     * In a turn a player must:
     *      - Draw a card from their draw deck.
     *      - Discard a random non-preferred card to their discard deck.
     *
     * During a player's turn, their action must be recorded in their adequate
     * player log.
     *
     * @throws IOException - when writing to file is not possible.
     */
    @Override
    public void takeTurn() throws IOException {

        // Generate array of integers from 0-3 in a random order.
        Integer[] numbers = {0,1,2,3};
        Collections.shuffle(Arrays.asList(numbers));

        // Loop through the random array so the card discarded is random.
        for (int i: numbers) {
            if (hand[i].getCardNumber() != playerID) {

                Card cardToDiscard = hand[i];

                // Draw a card.
                writeToFile(playerID + " has drawn a " + drawDeck.getTopCard().getCardNumber());
                hand[i] = drawCard();

                //Discard a card.
                writeToFile(playerID + " has discarded a " + cardToDiscard.getCardNumber());
                removeCard(cardToDiscard);

                writeToFile(playerID + " current hand: " + Arrays.toString(hand));
                break;
            }
        }
    }

    /**
     * Method to be applied once the player has finished their turn and has
     * a winning hand.
     * Other players must be informed about the winning player and game should
     * finish (i.e. threading should stop)
     *
     * This must be reflected in the player logs.
     *
     * @throws IOException - when writing to file is not possible.
     */
    @Override
    public void hasWon() throws IOException {
        done = true;
        winner = getPlayerID();
        writeToFile(playerID + " has informed the other players they've won");
        System.out.println("Player " + playerID + " has won");
    }

    /**
     * The thread method of player; combines the methods above treating them
     * as atomic actions where necessary.
     *
     * Once one player finishes, the other threads are informed and the
     * game finishes with a winner.
     */
    public void run() {

        // Add initial hand to player file.
        try {
            writeToFile(playerID + " initial hand: " + Arrays.toString(hand));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean win_on_start = false;  // Boolean to account for winning starting hand condition.

        // Thread loop.
        while (!done) {
            synchronized (this) {
                try {
                    // Let player take turn.
                    if (!isWin() && !win_on_start) {
                        takeTurn();
                    } else {
                        win_on_start = true;
                    }

                    // Check if player won this turn.
                    if (isWin() && !done) {
                        hasWon();
                    } else {
                        Thread.sleep(100); // Small delay between turns.
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Output end text for each player.
        try {
            if (playerID != winner) {
                writeToFile(playerID + " has been informed by " + winner + " that they've won");
            }
            writeToFile(playerID + " final hand: " + Arrays.toString(hand));
            writeToFile(playerID + " exits");
            drawDeck.writeToFile(playerID + " final deck: " + drawDeck.getDeck());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
