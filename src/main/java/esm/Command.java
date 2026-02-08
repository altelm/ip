package esm;

public class Command {

    private CommandType commandType;
    private int elemIndex;
    private String info;

    public enum CommandType {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, EMPTY, GIBBERSIH, BYE
    }

    public Command(CommandType commandType, int elemIndex) {
        this.commandType = commandType;
        this.elemIndex = elemIndex;
    }

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public Command(CommandType commandType, String info) {
        this.commandType = commandType;
        this.info = info;
    }

    public CommandType getType() {
        return this.commandType;
    }

    public int getIndex() {
        return this.elemIndex;
    }

    public String getInfo() {
        return this.info;
    }


}
