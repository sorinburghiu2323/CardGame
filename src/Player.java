public class Player implements PlayerInterface {
    private Card[] hand = new Card[4];
    private Player nextPlayer;

    public Player(Card[] hand, Player nextPlayer) {
        this.hand = hand;
        this.nextPlayer = nextPlayer;
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
}
