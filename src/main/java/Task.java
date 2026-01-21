public class Task {

    private String name;
    private String done;

    public Task(String name, String done) {
        this.name = name;
        this.done = done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getName(){
        return this.name;
    }

    public String getDone() {
        return this.done;
    }
}
