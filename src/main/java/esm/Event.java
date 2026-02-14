package esm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task of type event. An event includes a start and an end date.
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;
    private DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates an Event with a given name, start, and end date.
     * @param info array with the name of event in index 0, start date in index 1, and end date in index 2
     */
    public Event(String info, String startDate, String endDate) throws DateException {
        super("E", info);
        try {
            this.startDate = LocalDate.parse(startDate.trim());
            this.endDate = LocalDate.parse(endDate.trim());
        } catch (DateTimeParseException e) {
            throw new DateException("Thy should use format YYYY-MM-DD");
        }
    }

    /**
     * Returns the string representation of the event task.
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "(from: " + this.startDate.format(formattedDate) + " to: "
                + this.endDate.format(formattedDate) + ")";
    }
}
