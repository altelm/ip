package esm;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private String deadline;

    /**
     * Creates a Deadline task from the given array of nameAndDeadline
     * @param nameAndDeadline array with the name of the task in index 0 and the deadline in index 1
     */
    public Deadline(String[] nameAndDeadline) {
        super("D", nameAndDeadline[0]);
        this.deadline = nameAndDeadline[1].substring(3);
    }

    /**
     * Returns the String representation of this deadline task
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + this.deadline + ")";
    }
}
