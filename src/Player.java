import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
<<<<<<< HEAD
import java.util.Random;
=======
import java.util.Collections;
>>>>>>> 77f0ff54815207040d30fac9fe6e3cba6d83c995

/**
 *
 */
public class Player extends Thread implements PlayerInterface {

    private final int playerID;
    private Card[] hand;
    private CardDeck discardDeck;
    private CardDeck drawDeck;
    private BufferedWriter writer;
    private volatile static boolean done = false;
    private static int winner;

    /**
     * @param playerID
     * @param hand
     * @param fileName
     * @throws IOException
     */
    public Player(int playerID, Card[] hand, String fileName) throws IOException {
        this.playerID = playerID;
        this.hand = hand;
        this.writer = new BufferedWriter(new FileWriter(fileName));
    }

<<<<<<< HEAD
    /**
     * @param discard
     */
    public void setDiscard(CardDeck discard) {
        this.discard = discard;
    }

    /**
     * @param draw
     */
    public void setDraw(CardDeck draw) {
        this.draw = draw;
=======
    public void setDiscardDeck(CardDeck discardDeck) {
        this.discardDeck = discardDeck;
    }

    public void setDrawDeck(CardDeck drawDeck) {
        this.drawDeck = drawDeck;
    }

    public int getPlayerID() {
        return playerID;
>>>>>>> 77f0ff54815207040d30fac9fe6e3cba6d83c995
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

<<<<<<< HEAD
    /**
     * @return
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * @return
     * @throws IOException
     */
    public Card drawCard() throws IOException {
        return draw.removeCardFromTop();
    }

    /**
     * @param card
     * Remove card from the players hand, add the card to bottom of the
     * deck relevant to the players position
     */
=======
    @Override
    public Card drawCard() {
        return drawDeck.removeCardFromTop();
    }

    @Override
>>>>>>> 77f0ff54815207040d30fac9fe6e3cba6d83c995
    public void removeCard(Card card) {
        discardDeck.addCardToBottom(card);
    }

<<<<<<< HEAD
    /**
     * @return
     * Method for threads to use in order to check if the game has been won
     * by a player, check by iterating through player hand.
     */
=======
    @Override
>>>>>>> 77f0ff54815207040d30fac9fe6e3cba6d83c995
    public synchronized Boolean isWin() {

        for (int i=0; i<3; i++) {
            if (hand[i].getCardNumber() != hand[i+1].getCardNumber()) {
                return false;
            }
        }
        return true;
    }

<<<<<<< HEAD
    /**
     *Thread method of the player
     */
=======
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

    @Override
    public void hasWon() throws IOException {
        done = true;
        winner = getPlayerID();
        writeToFile(playerID + " has informed the other players they've won");
        System.out.println("Player " + playerID + " has won");
    }

>>>>>>> 77f0ff54815207040d30fac9fe6e3cba6d83c995
    public void run(){

        // Add initial hand to player file.
        try {
            writeToFile(playerID + " initial hand: " + Arrays.toString(hand));
        } catch (IOException e) {
            e.printStackTrace();
        }

<<<<<<< HEAD
        boolean win_on_start = false;
        Random random = new Random();
=======
        boolean win_on_start = false;  // Boolean to account for winning starting hand condition.
>>>>>>> 77f0ff54815207040d30fac9fe6e3cba6d83c995

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
     * @param message
     * @throws IOException
     * Method writing to the file corresponding to the player and deck.
     * Player actions such as hand, discarding and drawing will be written to the file,
     * whilst the decks will be printed at the end of the game with current cards remaining.
     */
    protected void writeToFile(String message) throws IOException {
        this.writer.write(message);
        this.writer.newLine();
        this.writer.flush();
    }
}

