package esm;

/**
 * Represents an Exception caused by invalid date input.
 */
public class DateException extends EsmException {
    /**
     * Creates a DateException with the given message.
     * @param message
     */
    public DateException(String message) {
        super(message);
    }
}
