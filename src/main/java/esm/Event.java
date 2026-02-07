package esm;

public class Event extends Task {

    private String start;
    private String end;
    public Event(String[] info) {
        super("E",  info[0]);
        this.start = info[1].substring(5);
        this.end = info[2].substring(3);
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + this.start + " to: " + this.end + ")";
    }
}
