import java.io.IOException;

/**
 * Interface for player.
 */
public interface PlayerInterface {

    /**
     * Method to draw card from the respective player's draw deck.
     *
     * @return Card - drawn.
     */
    Card drawCard();

    /**
     * Remove respective card from player's hand.
     *
     * @param card - to be removed.
     */
    void removeCard(Card card);

    /**
     * Analyse player's hand to see is it is a winning hand.
     * A winning hand must have 4 Cards with the same card number.
     *
     * @return Boolean - True if winning hand; otherwise False.
     */
    Boolean isWin();

    /**
     * Method to combine the other method to create the effect of a "turn".
     * In a turn a player must:
     *      - Draw a card from their draw deck.
     *      - Discard a random non-preferred card to their discard deck.
     *
     * During a player's turn, their action must be recorded in their adequate
     * player log.
     *
     * @throws IOException
     */
    void takeTurn() throws IOException;

    /**
     * Method to be applied once the player has finished their turn and has
     * a winning hand.
     * Other players must be informed about the winning player and game should
     * finish (i.e. threading should stop)
     *
     * This must be reflected in the player logs.
     *
     * @throws IOException
     */
    void hasWon() throws IOException;
}
