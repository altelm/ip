package esm;

import java.util.Scanner;

public class Ui {

    public void run (Storage storage)  {
        Scanner scanner = new Scanner(System.in);
        TaskList tasklist = new TaskList(storage.load());
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
                    case Command.Type.EMPTY:
                        System.out.println("Words are required, lest thy task vanish into nothingness.");
                        break;
                    case Command.Type.LIST:
                        tasklist.print();
                        break;
                    case Command.Type.MARK: {
                        int index = getIndex(tasklist, command.getIndex());
                        tasklist.mark(index);
                        System.out.println("Lo, I have set this task down as finished:)\n" + tasklist.getTask(index));
                        break;
                    }
                    case Command.Type.UNMARK: {
                        int index = getIndex(tasklist, command.getIndex());
                        tasklist.unmark(index);
                        System.out.println("It is undone, and so marked in thy ledger:(\n" + tasklist.getTask(index));
                        break;
                    }
                    case Command.Type.DELETE: {
                        int index = getIndex(tasklist, command.getIndex());
                        Task temptask = tasklist.remove(index);
                        System.out.println("Thy request is heeded—the task is expunged.\n" + temptask +
                                "\nThy ledger now holdeth " + tasklist.getSize() + " task(s)");
                        break;
                    }
                    case Command.Type.TODO: {
                        Task temptask = new ToDo(command.getInfo());
                        tasklist.add(temptask);
                        System.out.println("Aye tis done.\n" + temptask + "\nThy ledger now holdeth "
                                + tasklist.getSize() + " task(s)");
                        break;
                    }
                    case Command.Type.DEADLINE: {
                        Task temptask = new Deadline(command.getInfo().split("/"));
                        tasklist.add(temptask);
                        System.out.println("Aye tis done.\n" + temptask + "\nThy ledger now holdeth "
                                + tasklist.getSize() + " task(s)");
                        break;
                    }
                    case Command.Type.EVENT: {
                        Task temptask = new Event(command.getInfo().split("/"));
                        tasklist.add(temptask);
                        System.out.println("Aye tis done.\n" + temptask + "\nThy ledger now holdeth "
                                + tasklist.getSize() + " task(s)");
                        break;
                    }
                    case Command.Type.GIBBERSIH:
                        System.out.println("I confess myself ignorant of thy intent");
                        break;
                    case Command.Type.BYE:
                        storage.save(tasklist.getList());
                        System.out.println("Fare thee well\n");
                        return;
                }
            } catch (ParserException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getIndex(TaskList tasklist, int i) throws ParserException {
        if (tasklist.getSize() == 0) {
            throw new ParserException("Thy ledger is empty");
        } else if (i < 0 || i > tasklist.getSize()) {
            throw new ParserException("Thou hath given an non-existeth ledger number");
        }

        return i;
    }
}