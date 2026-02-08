package esm;

public class Task {

    private String name;
    private String isDone;
    private String type;


    public Task(String type, String name) {
        this.name = name;
        this.isDone = "";
        this.type = type;
    }

    public void setDone(String done) {
        this.isDone = done;
    }

    public String getName(){
        return this.name;
    }

    public String getDone() {
        return this.isDone;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + this.isDone + "] " + this.name;
    }
}
