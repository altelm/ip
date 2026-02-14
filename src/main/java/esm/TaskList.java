package esm;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Creates a TaskList from the given arraylist of tasks.
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Marks the specific element in the list given by the index as done.
     *
     * @param i index of the task element.
     */
    public void mark(int i) {
        if (i < 0 || i > getSize()) {
            return;
        }
        this.taskList.get(i - 1).setDone("X");
    }

    /**
     * Marks the specific element in the list given by the index as not done.
     *
     * @param i index of the task element.
     */
    public void unmark(int i) {
        if (i < 0 || i > getSize()) {
            return;
        }
        this.taskList.get(i - 1).setDone("");
    }

    /**
     * Removes and then returns a specific element from the tasklist.
     *
     * @param i index of the element.
     * @return
     */
    public Task remove(int i) {
        if (i < 0 || i > this.taskList.size()) {
            return null;
        }
        return this.taskList.remove(i - 1);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task task to be added to list.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the arraylist format of the current task list.
     *
     * @return
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Returns a specific element in the task list given by the index.
     *
     * @param i index of element to be returned.
     * @return
     */
    public Task getTask(int i) {
        if (i < 0 || i > this.taskList.size()) {
            return null;
        }
        return this.taskList.get(i - 1);
    }

    /**
     * Returns the size of the task list.
     *
     * @return
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Finds all the tasks with the same info and returns them
     */
    public TaskList find(String info) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getName().contains(info)) {
                tasks.add(this.taskList.get(i));
            }
        }
        return new TaskList(tasks);
    }

    /**
     * Prints the list of tasks in order, or informs the user if the list is empty.
     */
    public String print() {
        if (getSize() == 0) {
            return ("Thy ledger is empty");
        }

        String toPrint = "";
        for (int i = 1; i <= this.taskList.size(); i++) {
            toPrint += (i + ". " + this.taskList.get(i - 1)) + "\n";
        }
        return toPrint;
    }
}
