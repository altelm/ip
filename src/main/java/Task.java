public class Task {

    private String name;
    private String done;
    private String type;


    public Task(String type, String name) {
        this.name = name;
        this.done = "";
        this.type = type;
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

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + this.done + "] " + this.name;
    }
}
