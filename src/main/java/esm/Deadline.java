package esm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private LocalDate deadline;
    private DateTimeFormatter out = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a Deadline task from the given array of nameAndDeadline
     * @param name name of task to be done
     * @param deadline date deadline of task
     */
    public Deadline(String name, String deadline) throws DateException {
        super("D", name);
        try {
            this.deadline = LocalDate.parse(deadline.trim());
        } catch (DateTimeParseException e) {
            throw new DateException("Thy should use format YYYY-MM-DD");
        }

    }

    /**
     * Returns the String representation of this deadline task
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + this.deadline.format(out) + ")";
    }
}
