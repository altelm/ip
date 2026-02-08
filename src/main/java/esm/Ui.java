package esm;

import java.util.Scanner;

/**
 * Handles the user interaction and user commands
 */
public class Ui {

    /**
     * Runs the user interaction platform and updates the tasklist according the user command
     * @param tasklist
     * @return
     */
    public TaskList run (TaskList tasklist)  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Greetings I am thy humble Esm\nSpeak and I shall head the!\n");

        while (true) {
            String in = scanner.nextLine();
            Command command;
            try {
                command = Parser.parseInput(in);
            } catch (ParserException e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                switch (command.getType()) {
                    case EMPTY:
                        System.out.println("Words are required, lest thy task vanish into nothingness.");
                        break;
                    case LIST:
                        tasklist.print();
                        break;
                    case MARK: {
                        int index = getIndex(tasklist, command.getIndex());
                        tasklist.mark(index);
                        System.out.println("Lo, I have set this task down as finished:)\n" + tasklist.getTask(index));
                        break;
                    }
                    case UNMARK: {
                        int index = getIndex(tasklist, command.getIndex());
                        tasklist.unmark(index);
                        System.out.println("It is undone, and so marked in thy ledger:(\n" + tasklist.getTask(index));
                        break;
                    }
                    case DELETE: {
                        int index = getIndex(tasklist, command.getIndex());
                        Task temptask = tasklist.remove(index);
                        System.out.println("Thy request is heeded—the task is expunged.\n" + temptask +
                                "\nThy ledger now holdeth " + tasklist.getSize() + " task(s)");
                        break;
                    }
                    case TODO: {
                        Task temptask = new ToDo(command.getInfo());
                        tasklist.add(temptask);
                        System.out.println("Aye tis done.\n" + temptask + "\nThy ledger now holdeth "
                                + tasklist.getSize() + " task(s)");
                        break;
                    }
                    case DEADLINE: {
                        Task temptask = new Deadline(command.getInfo().split("/"));
                        tasklist.add(temptask);
                        System.out.println("Aye tis done.\n" + temptask + "\nThy ledger now holdeth "
                                + tasklist.getSize() + " task(s)");
                        break;
                    }
                    case EVENT: {
                        Task temptask = new Event(command.getInfo().split("/"));
                        tasklist.add(temptask);
                        System.out.println("Aye tis done.\n" + temptask + "\nThy ledger now holdeth "
                                + tasklist.getSize() + " task(s)");
                        break;
                    }
                    case GIBBERISH:
                        System.out.println("I confess myself ignorant of thy intent");
                        break;
                    case BYE:
                        System.out.println("Fare thee well\n");
                        return tasklist;
                }
            } catch (ParserException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Returns the index of the element a certain command will act on
     * @param tasklist tasklist of the user
     * @param i index of the elment to be manipulated
     * @return
     * @throws ParserException
     */
    private int getIndex(TaskList tasklist, int i) throws ParserException {
        if (tasklist.getSize() == 0) {
            throw new ParserException("Thy ledger is empty");
        } else if (i < 0 || i > tasklist.getSize()) {
            throw new ParserException("Thou hath given an non-existeth ledger number");
        }

        return i;
    }
}