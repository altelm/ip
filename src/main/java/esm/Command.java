package esm;

/**
 * Represents a user command. A command includes the type of task and relevant index
 * where applicable as well as the extra information.
 */
public class Command {

    private CommandType commandType;
    private int elemIndex;
    private String info;
    private String deadline;
    private String startDate;
    private String endDate;

    /**
     * Represents the type of user command.
     */
    public enum CommandType {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, EMPTY, GIBBERSIH, BYE, FIND, HELP, SORT;
    }
    /**
     * Creates a command with the specified type and element index.
     *
     * @param commandType type of command.
     * @param elemIndex   index of element in the array that command will work on.
     */
    public Command(CommandType commandType, int elemIndex) {
        this.commandType = commandType;
        this.elemIndex = elemIndex;
        this.info = "";
        this.deadline = "";
        this.startDate = "";
        this.endDate = "";
    }

    /**
     * Creates a command with the specified type.
     *
     * @param commandType type of command.
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.info = "";
        this.deadline = "";
        this.elemIndex = -1;
        this.startDate = "";
        this.endDate = "";
    }

    /**
     * Creates a command with the specified type and required information.
     *
     * @param commandType type of command.
     */
    public Command(CommandType commandType, String info, String deadline) {
        this.commandType = commandType;
        this.info = info;
        this.deadline = deadline;
        this.elemIndex = -1;
        this.startDate = "";
        this.endDate = "";
    }

    /**
     * Creates a command with the specified type and required information.
     *
     * @param commandType type of command.
     * @param info info required for the command.
     */
    public Command(CommandType commandType, String info) {
        this.commandType = commandType;
        this.info = info;
        this.elemIndex = -1;
        this.deadline = "";
        this.startDate = "";
        this.endDate = "";
    }

    /**
     * Creates a command with the specified type and required information.
     *
     * @param commandType type of command.
     * @param info info required for the command.
     */
    public Command(CommandType commandType, String info, String startDate, String endDate) {
        this.commandType = commandType;
        this.info = info;
        this.elemIndex = -1;
        this.deadline = "";
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the type of command for the user command.
     *
     * @return
     */
    public CommandType getType() {
        return this.commandType;

    }

    /**
     * Returns the index of the task associated with the specific command.
     *
     * @return
     */
    public int getIndex() {
        return this.elemIndex;
    }

    /**
     * Returns the required info for the task associated with the specific command.
     *
     * @return
     */
    public String getInfo() {
        return this.info;
    }

    /**
     * Returns the deadline of task of type deadline.
     *
     * @return
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the start date of task of type event.
     *
     * @return
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Returns the end date of task of type event.
     *
     * @return
     */
    public String getEndDate() {
        return this.endDate;
    }
}
