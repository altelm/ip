package esm;

/**
 * Represents a user command. A command includes the type of task and relevant index
 * where applicable as well as the extra information
 */
public class Command {

    private Type type;
    private int elemIndex;
    private String info;

    /**
     * Represents the type of user command
     */
    public enum Type {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, EMPTY, GIBBERISH, BYE
    }

    /**
     * Creates a command with the specified type and element index
     * @param type
     * @param elemIndex
     */
    public Command(Type type, int elemIndex) {
        this.type = type;
        this.elemIndex = elemIndex;
    }

    /**
     * Creates a command with the specified type
     * @param type
     */
    public Command(Type type) {
        this.type = type;
    }

    /**
     * Creates a command with the specified type and required information
     * @param type
     * @param info
     */
    public Command(Type type, String info) {
        this.type = type;
        this.info = info;
    }

    /**
     * Returns the type of command for the user command
     * @return
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Returns the index of the task associated with the specific command
     * @return
     */
    public int getIndex() {
        return this.elemIndex;
    }

    /**
     * Returns the required info for the task associated with the specific command
     * @return
     */

    public String getInfo() {
        return this.info;
    }

}
