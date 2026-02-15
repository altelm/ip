package esm;

/**
 * Represents an Exception that is caused by any Esm file.
 */
public class EsmException extends Exception {

    /**
     * Creates an EsmException with the given message.
     * @param message the message to be shown when the exception is thrown.
     */
    public EsmException(String message) {
        super(message);
    }
}
