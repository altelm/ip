package esm;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Entry point for the ESM.
 * Starts and end the session for ESM application by setting up the UI and Storage (loading and saving the file).
 */
public class Esm {

    private Path path;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a Esm object
     */
    public Esm() {
        this.path = Paths.get("data", "esm.txt");
        this.storage = new Storage(path);
        this.taskList = new TaskList(storage.loadFile());

    }

    /**
     * Starts ESM application
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Greetings I am thy humble Esm\nSpeak and I shall head the!\n");
    }

    /**
     * Return the response to user input
     */
    public String getResponse(String in) {
        Command command;
        try {
            command = Parser.parseInput(in);
        } catch (ParserException e) {
            return (e.getMessage());
        }


        switch (command.getType()) {
        case EMPTY:
            return ("Words are required, lest thy task vanish into nothingness.");
        case LIST:
            return taskList.print();
        case MARK: {
            int index = getIndex(taskList, command.getIndex());
            if (index == -1) {
                return "Thy ledger is empty";
            } else if (index == -2) {
                return "Thou hath given an non-existeth ledger number";
            }
            taskList.mark(index);
            return ("Lo, I have set this task down as finished:)\n" + taskList.getTask(index));
        }
        case UNMARK: {
            int index = getIndex(taskList, command.getIndex());
            taskList.unmark(index);
            return ("It is undone, and so marked in thy ledger:(\n" + taskList.getTask(index));
        }
        case DELETE: {
            int index = getIndex(taskList, command.getIndex());
            Task temptask = taskList.remove(index);
            return ("Thy request is heeded—the task is expunged.\n" + temptask
                    + "\nThy ledger now holdeth " + taskList.getSize() + " task(s)");
        }
        case TODO: {
            Task temptask = new ToDo(command.getInfo());
            taskList.add(temptask);
            return ("Aye tis done.\n" + temptask + "\nThy ledger now holdeth "
                    + taskList.getSize() + " task(s)");
        }
        case DEADLINE: {
            Task temptask = new Deadline(command.getInfo().split("/"));
            taskList.add(temptask);
            return ("Aye tis done.\n" + temptask + "\nThy ledger now holdeth "
                    + taskList.getSize() + " task(s)");
        }
        case EVENT: {
            Task temptask = new Event(command.getInfo().split("/"));
            taskList.add(temptask);
            return ("Aye tis done.\n" + temptask + "\nThy ledger now holdeth "
                    + taskList.getSize() + " task(s)");
        }
        case FIND: {
            TaskList tasks = taskList.find(command.getInfo());
            return tasks.print();
        }
        case GIBBERSIH:
            return ("I confess myself ignorant of thy intent");
        case BYE: {
            storage.saveFile(taskList.getList());
            return ("Fare thee well\n");
        }
        default:
            return ("Seems you find the loophole");
        }
    }

    /**
     * Returns the index of the element a certain command will act on
     *
     * @param tasklist tasklist of the user
     * @param i        index of the elment to be manipulated
     * @return
     */
    private int getIndex(TaskList tasklist, int i) {
        if (tasklist.getSize() == 0) {
            return -1;
        } else if (i < 0 || i > tasklist.getSize()) {
            return -2;
        }
        return i;
    }
}
