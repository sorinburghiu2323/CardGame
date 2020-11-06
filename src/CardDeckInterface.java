/**
 * Interface for card deck.
 */
public interface CardDeckInterface {

    /**
     * Add a given card to the bottom of the current deck.
     *
     * @param card - to be added to bottom.
     */
    void addCardToBottom(Card card);

    /**
     * Remove and return the current card from the top of the deck.
     *
     * @return Card - that was removed.
     */
    Card removeCardFromTop();

    /**
     * Get the current card on the top of the deck.
     *
     * @return Card - that is on top.
     */
    Card getTopCard();
}
