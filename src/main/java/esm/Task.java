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
    private String done;
    private String type;

    /**
     * Creates a task with a specified name and type. Upon creation a task is considered as not done.
     * @param type type of task
     * @param name name of task
     */
    public Task(String type, String name) {
        this.name = name;
        this.done = "";
        this.type = type;
    }

    /**
     * Marks the task as done.
     * @param done
     */
    public void setDone(String done) {
        this.done = done;
    }

    /**
     * Returns the name of the task.
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns weither the task is done or not
     */
    public String getDone() {
        return this.done;
    }

    /**
     * Returns the type of the task.
     * @return
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the string representation of the task.
     * @return
     */
    @Override
    public String toString() {
        return "[" + this.type + "][" + this.done + "] " + this.name;
    }
}
