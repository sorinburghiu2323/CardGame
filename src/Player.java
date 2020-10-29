import java.util.Arrays;

public class Player extends Thread implements PlayerInterface {

    private final int playerID;
    private Card[] hand;
    private CardDeck discard;
    private CardDeck draw;
    private static boolean done = false;

    public Player(int playerID, Card[] hand) {
        this.playerID = playerID;
        this.hand = hand;
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

    public CardDeck getDiscard() {
        return discard;
    }

    public CardDeck getDraw() {
        return draw;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public Card drawCard() {
        return draw.removeCardFromTop();
    }

    public void removeCard(Card card) {
        discard.addCardToBottom(card);
    }

    public Boolean isWin() {
        for (int i=0; i<4; i++) {
            if (hand[i].getCardNumber() != playerID) {
                return false;
            }
        }
        return true;
    }

    public void run(){
        while (!done) {
            synchronized (this) {
                try {
                    for (int i=0; i<4; i++) {
                        if (hand[i].getCardNumber() != playerID) {
                            Card tempCard = hand[i];
                            hand[i] = drawCard();
                            removeCard(tempCard);
                            break;
                        }
                    }
                    if (isWin()) {
                        System.out.println(playerID + " has won.");
                        notifyAll();
                        this.done = true;
                    }
                    else {
                        System.out.println(playerID + ": " + Arrays.toString(hand) + " finished turn.");
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

