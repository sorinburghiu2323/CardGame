import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Player extends Thread implements PlayerInterface {

    private int playerID;
    private Card[] hand;
    private CardDeck discardDeck;
    private CardDeck drawDeck;
    private BufferedWriter writer;
    private volatile static boolean done = false;
    private static int winner;

    public Player(int playerID, Card[] hand, String fileName) throws IOException {
        this.playerID = playerID;
        this.hand = hand;
        this.writer = new BufferedWriter(new FileWriter(fileName));
    }

    public void setDiscardDeck(CardDeck discardDeck) {
        this.discardDeck = discardDeck;
    }

    public void setDrawDeck(CardDeck drawDeck) {
        this.drawDeck = drawDeck;
    }

    public int getPlayerID() {
        return playerID;
    }
    public void setPlayerID(int id){
        this.playerID = id;
    }

    public void setHand(CardDeck cardDeck){
        for (int i = 0; i < 4; i++) {
            this.hand[i] = cardDeck.removeCardFromTop();
        }
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

    @Override
    public Card drawCard() {
        return drawDeck.removeCardFromTop();
    }

    @Override
    public void removeCard(Card card) {
        discardDeck.addCardToBottom(card);
    }

    @Override
    public synchronized Boolean isWin() {

        for (int i=0; i<3; i++) {
            if (hand[i].getCardNumber() != hand[i+1].getCardNumber()) {
                return false;
            }
        }
        return true;
    }

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

    public void run(){

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

    protected void writeToFile(String message) throws IOException {
        this.writer.write(message);
        this.writer.newLine();
        this.writer.flush();
    }
}

