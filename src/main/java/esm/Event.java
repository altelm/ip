package esm;

public class Event extends Task {

    private String startDate;
    private String endDate;
    public Event(String[] info) {
        super("E",  info[0]);
        this.startDate = info[1].substring(5);
        this.endDate = info[2].substring(3);
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
