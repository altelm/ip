package esm;

/**
 * Represents a task to accomplish. A task is represented by the type of task, the name,
 * and whether it is done or not.
 */
public class Task {

    private String name;
    private boolean isDone;
    private String type;

    /**
     * Creates a task with a specified name and type.
     * Upon creation a task is considered not done.
     *
     * @param type the type of task
     * @param name the name of task
     */
    public Task(String type, String name) {
        this.name = name;
        this.isDone = false;
        this.type = type;
    }


    public String getName() {
        return this.name;
    }

    public String getDone() {
        return this.isDone ? "X" : "";
    }


    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Returns the sort date of the task, if applicable.
     *
     * @return the sort date, or {@code null} if not applicable
     */
    public String getSortDate() {
        return null;
    }

    /**
     * Returns a string representation of the task in the format: [type][isDone] name
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getDone() + "] " + this.name;
    }
}
