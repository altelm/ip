import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDate deadline;
    private static final DateTimeFormatter PRI = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String[] nameAndDeadline) {
        super("D", nameAndDeadline[0]);
        System.out.println(nameAndDeadline[1]);
        try {
            this.deadline = LocalDate.parse(nameAndDeadline[1].trim().split("\\s+")[1]);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Holdeth and useth yyyy-mm-dd");
        }
    }


    @Override
    public String toString() {
        return super.toString() + "(by: " + this.deadline.format(PRI) + ")";
    }
}
