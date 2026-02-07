package esm;

public class Command {

    private Type type;
    private int elemIndex;
    private String info;

    public enum Type {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, EMPTY, GIBBERSIH, BYE
    }

    public Command(Type type, int elemIndex) {
        this.type = type;
        this.elemIndex = elemIndex;
    }

    public Command(Type type) {
        this.type = type;
    }

    public Command(Type type, String info) {
        this.type = type;
        this.info = info;
    }

    public Type getType() {
        return this.type;
    }

    public int getIndex() {
        return this.elemIndex;
    }

    public String getInfo() {
        return this.info;
    }


}
