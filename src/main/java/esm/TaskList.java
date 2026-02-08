package esm;

import java.util.ArrayList;
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void mark(int i) {
        this.taskList.get(i - 1).setDone("X");
    }

    public void unmark(int i) {
        this.taskList.get(i - 1).setDone("");
    }

    public Task remove(int i) {
        if(i < 0 || i > this.taskList.size()) {
            return null;
        }
        return this.taskList.remove(i-1);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public Task getTask(int i) {
        if (i < 0 || i > this.taskList.size()) {
            return null;
        }
        return this.taskList.get(i-1);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void print() {
        if(getSize() == 0) {
            System.out.println("Thy ledger is empty");
            return;
        }
        for(int i = 1; i <= this.taskList.size(); i++) {
            System.out.println( i + ". " + this.taskList.get(i-1));
        }
    }
}
