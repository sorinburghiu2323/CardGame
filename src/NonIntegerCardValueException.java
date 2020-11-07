/**
 * Exception to be thrown when a card has a non integer value.
 * A card must have a positive integer value.
 */
public class NonIntegerCardValueException extends Exception {

    /**
     * Constructs an instance of the exception containing the message argument.
     *
     * @param message - String containing details regarding the exception cause.
     */
    public NonIntegerCardValueException(String message) {
        super(message);
    }
}