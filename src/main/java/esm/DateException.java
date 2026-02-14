package esm;

/**
 * Represents an Exception that is caused by incorrect date inputs
 */
public class DateException extends EsmException {
    public DateException(String message) {
        super(message);
    }
}
