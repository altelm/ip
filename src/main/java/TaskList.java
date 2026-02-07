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
        return this.tasklist.remove(i-1);
    }

    public void add(Task task) {
        this.tasklist.add(task);
    }

    public ArrayList<Task> getList() {
        return this.tasklist;
    }

    public Task getTask(int i) {
        return this.tasklist.get(i-1);
    }

    public int getSize() {
        return this.tasklist.size();
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
