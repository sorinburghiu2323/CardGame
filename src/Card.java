public class Card{
    private final int cardNumber;

    public Card(int cardNumber){
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return Integer.toString(cardNumber);
    }
}
