package esm;

/**
 * Represents a user command. A command includes the type of task and relevant index
 * where applicable as well as the extra information
 */
public class Command {

    private CommandType commandType;
    private int elemIndex;
    private String info;

    /**
     * Represents the type of user command
     */
    public enum CommandType {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, EMPTY, GIBBERSIH, BYE, FIND
    }

    /**
     * Creates a command with the specified type and element index
     * @param commandType type of command
     * @param elemIndex index of element in the array that command will work on
     */
    public Command(CommandType commandType, int elemIndex) {
        this.commandType = commandType;
        this.elemIndex = elemIndex;
    }

    /**
     * Creates a command with the specified type
     * @param commandType type of command
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Creates a command with the specified type and required information
     * @param commandType type of command
     * @param info info required for the command
     */
    public Command(CommandType commandType, String info) {
        this.commandType = commandType;
        this.info = info;
    }

    /**
     * Returns the type of command for the user command
     * @return
     */
    public CommandType getType() {
        return this.commandType;

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
