/**
 * Exception to be thrown when a card was inputted with a negative value.
 * A card can only have positive values.
 */
public class NegativeCardValueException extends Exception {

    /**
     * Constructs an instance of the exception containing the message argument.
     *
     * @param message - String containing details regarding the exception cause.
     */
    public NegativeCardValueException(String message) {
        super(message);
    }
}