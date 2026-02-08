package esm;

/**
 * Represent a task that should be done.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo task.
     * @param name name of task to be done.
     */
    public ToDo(String name) {
        super("T", name);
    }
}
