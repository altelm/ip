package esm;

/**
 * Represents an Exception that is caused from parsing the user input.
 */
public class ParserException extends Exception{
    public ParserException(String message) {
        super(message);
    }
}
