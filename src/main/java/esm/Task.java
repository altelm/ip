package esm;

/**
 * Represents a task to accomplish. A task is represented by the type of task, the name,
 * and weither it is done or not.
 */
public class Task {

    /**
     * If task is done is represented as string.
     */
    private String name;
    private String isDone;
    private String type;

    /**
     * Creates a task with a specified name and type. Upon creation a task is considered as not done.
     * @param type type of task
     * @param name name of task
     */
    public Task(String type, String name) {
        this.name = name;
        this.isDone = "";
        this.type = type;
    }

    /**
     * Marks the task as done.
     * @param done
     */
    public void setDone(String done) {
        this.isDone = done;
    }

    /**
     * Returns the name of the task.
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns weither the task is done or not
     */
    public String getDone() {
        return this.isDone;
    }

    /**
     * Returns the type of the task.
     * @return
     */
    public String getType() {
        return this.type;
    }

    public String getSortDate() {
        return null;
    }

    /**
     * Returns the string representation of the task.
     * @return
     */
    @Override
    public String toString() {
        return "[" + this.type + "][" + this.isDone + "] " + this.name;
    }
}
