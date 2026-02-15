package esm;

/**
 * Represents an Exception that is caused from parsing the user input.
 */
public class ParserException extends EsmException {

    /**
     * Creates a ParserException with the specified message.
     * @param message
     */
    public ParserException(String message) {
        super(message);
    }

}
