import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Player extends Thread implements PlayerInterface {

    private final int playerID;
    private Card[] hand;
    private CardDeck discard;
    private CardDeck draw;
    private volatile static boolean done = false;
    private BufferedWriter writer;
    private static int winner;

    public Player(int playerID, Card[] hand, String fileName) throws IOException {
        this.playerID = playerID;
        this.hand = hand;
        this.writer = new BufferedWriter(new FileWriter(fileName));
    }

    public void setDiscard(CardDeck discard) {
        this.discard = discard;
    }

    public void setDraw(CardDeck draw) {
        this.draw = draw;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID=" + playerID +
                ", hand=" + Arrays.toString(hand) +
                ", discard=" + discard +
                ", draw=" + draw +
                '}';
    }

    public int getPlayerID() {
        return playerID;
    }

    public Card drawCard() throws IOException {
        return draw.removeCardFromTop();
    }

    public void removeCard(Card card) {
        discard.addCardToBottom(card);
    }

    public synchronized Boolean isWin() {

        for (int i=0; i<3; i++) {
            if (hand[i].getCardNumber() != hand[i+1].getCardNumber()) {
                return false;
            }
        }
        return true;
    }

    public void run(){

        try {
            writeToFile(playerID + " initial hand: " + Arrays.toString(hand));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean win_on_start = false;

        while (!done) {
            synchronized (this) {
                try {
                    if (!isWin() && !win_on_start) {
                        for (int i = 0; i < 4; i++) {
                            if (hand[i].getCardNumber() != playerID) {

                                Card tempCard = hand[i];
                                writeToFile(playerID + " has drawn a " + draw.getTopCard().getCardNumber());
                                hand[i] = drawCard();

                                writeToFile(playerID + " has discarded a " + tempCard.getCardNumber());
                                removeCard(tempCard);

                                writeToFile(playerID + " current hand: " + Arrays.toString(hand));

                                break;
                            }
                        }
                    }
                    else {
                        win_on_start = true;
                    }
                    if (isWin() && !done) {
                        done = true;
                        winner = getPlayerID();
                        writeToFile(playerID + " has informed the other players they've won");
                    } else {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            if (playerID != winner) {
                writeToFile(playerID + " has been informed by " + winner + " that they've won");
            }
            writeToFile(playerID + " final hand: " + Arrays.toString(hand));
            writeToFile(playerID + " exits");
            draw.writeToFile(playerID + " final deck: " + draw.getDeck());
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

