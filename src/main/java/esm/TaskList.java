package esm;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasklist;

    /**
     * Creates a TaskList from the given arraylist of tasks.
     * @param tasklist
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Marks the specific element in the list given by the index as done.
     * @param i index of the task element.
     */
    public void mark(int i) {
        this.tasklist.get(i - 1).setDone("X");
    }

    /**
     * Marks the specific element in the list given by the index as not done.
     * @param i index of the task element.
     */
    public void unmark(int i) {
        this.tasklist.get(i - 1).setDone("");
    }

    /**
     * Removes and then returns a specific element from the tasklist.
     * @param i index of the element.
     * @return
     */
    public Task remove(int i) {
        if(i < 0 || i > this.tasklist.size()) {
            return null;
        }
        return this.tasklist.remove(i-1);
    }

    /**
     * Adds a task to the task list.
     * @param task task to be added to list.
     */
    public void add(Task task) {
        this.tasklist.add(task);
    }

    /**
     * Returns the arraylist format of the current task list.
     * @return
     */
    public ArrayList<Task> getList() {
        return this.tasklist;
    }

    /**
     * Returns a specific element in the task list given by the index.
     * @param i index of element to be returned.
     * @return
     */
    public Task getTask(int i) {
        if (i < 0 || i > this.tasklist.size()) {
            return null;
        }
        return this.tasklist.get(i-1);
    }

    /**
     * Returns the size of the task list.
     * @return
     */
    public int getSize() {
        return this.tasklist.size();
    }

    /**
     * Prints the currents status of the task list.
     */
    public void print() {
        if(getSize() == 0) {
            System.out.println("Thy ledger is empty");
            return;
        }
        for(int i = 1; i <= this.tasklist.size(); i++) {
            System.out.println( i + ". " + this.tasklist.get(i-1));
        }
    }
}
