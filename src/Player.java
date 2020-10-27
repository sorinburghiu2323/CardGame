import java.util.Arrays;
import java.util.LinkedList;

public class Player implements PlayerInterface {

    private int playerID;
    private Card[] hand = new Card[4];
    private CardDeck discard;
    private CardDeck draw;
    private Player nextPlayer;

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


    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID=" + playerID +
                ", hand=" + Arrays.toString(hand) +
                ", discard=" + discard +
                ", draw=" + draw +
                ", nextPlayer=" + nextPlayer +
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

    public Player getNextPlayer() {
        return nextPlayer;
    }


    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    @Override
    public Card drawCard(CardDeck cardDeck) {
        return null;
    }

    @Override
    public void removeCard(CardDeck cardDeck, Card card) {

    }

    @Override
    public Boolean isWin() {
        return null;
    }


    public void startTurn() {
        while (true){
            synchronized (this){

            }
        }
    }

}

