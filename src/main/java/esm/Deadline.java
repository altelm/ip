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
     * @param nameAndDeadline array with the name of the task in index 0 and the deadline in index 1
     */
    public Deadline(String[] nameAndDeadline) {
        super("D", nameAndDeadline[0]);

        try {
            this.deadline = LocalDate.parse(nameAndDeadline[1].trim());
        } catch (DateTimeParseException e) {
            System.out.println(
                    "Thy should use format YYYY-MM-DD");
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
