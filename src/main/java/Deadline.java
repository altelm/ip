public class Deadline extends Task{

    private String deadline;
    public Deadline(String[] nameAndDeadline) {
        super("D", nameAndDeadline[0]);
        this.deadline = nameAndDeadline[1].substring(3);
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + this.deadline + ")";
    }
}
