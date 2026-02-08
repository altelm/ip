package esm;

import java.util.ArrayList;
public class TaskList {

    private ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    public void mark(int i) {
        this.tasklist.get(i - 1).setDone("X");
    }

    public void unmark(int i) {
        this.tasklist.get(i - 1).setDone("");
    }

    public Task remove(int i) {
        if(i < 0 || i > this.tasklist.size()) {
            return null;
        }
        return this.tasklist.remove(i-1);
    }

    public void add(Task task) {
        this.tasklist.add(task);
    }

    public ArrayList<Task> getList() {
        return this.tasklist;
    }

    public Task getTask(int i) {
        if (i < 0 || i > this.tasklist.size()) {
            return null;
        }
        return this.tasklist.get(i-1);
    }

    public int getSize() {
        return this.tasklist.size();
    }

    public TaskList find(String info) {
        ArrayList<Task> tasks = new  ArrayList<>();
        for(int i = 0; i < this.tasklist.size(); i++) {
            if(this.tasklist.get(i).getName().contains(info)) {
                tasks.add(this.tasklist.get(i));
            }
        }
        return new TaskList(tasks);
    }

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
