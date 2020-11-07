/**
 * Exception to be thrown when an invalid number of cards is added.
 * A valid number of cards consists of: 8 * numberOfPlayers.
 */
public class InvalidNumberOfCardsException extends Exception {

    /**
     * Constructs an instance of the exception containing the message argument.
     *
     * @param message - String containing details regarding the exception cause.
     */
    public InvalidNumberOfCardsException(String message) {
        super(message);
    }
}
