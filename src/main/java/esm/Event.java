package esm;

/**
 * Represents a task of type event. An event includes a start and an end date.
 */
public class Event extends Task {

    private String startDate;
    private String endDate;

    /**
     * Creates an Event with a given name, start, and end date.
     * @param info array with the name of event in index 0, start date in index 1, and end date in index 2
     */
    public Event(String[] info) {
        super("E", info[0]);
        this.startDate = info[1].substring(5);
        this.endDate = info[2].substring(3);
    }

    /**
     * Returns the string representation of the event task.
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "(from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
