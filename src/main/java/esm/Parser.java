package esm;

/**
 * Parses user input to extract required data.
 */
public class Parser {

    /**
     * Parses a single user input to extract the command to be executed.
     *
     * @param input user input
     * @return
     * @throws ParserException
     */
    public static Command parseInput(String input) throws ParserException {
        if (input == null || input.trim().isEmpty()) {
            return new Command(Command.CommandType.EMPTY);
        }

        String[] parts = input.trim().split("\\s+", 2);
        String info = (parts.length == 2) ? parts[1].trim() : "";
        String command = parts[0].toLowerCase();

        switch (command) {
        case "list":
            return new Command(Command.CommandType.LIST);
        case "bye":
            return new Command(Command.CommandType.BYE);
        case "delete":
            return new Command(Command.CommandType.DELETE, parseIndex(info));
        case "mark":
            return new Command(Command.CommandType.MARK, parseIndex(info));
        case "unmark":
            return new Command(Command.CommandType.UNMARK, parseIndex(info));
        case "todo":
            return parseTodo(info);
        case "deadline":
            return parseDeadline(parts[1], info);
        case "event":
            return parseEvent(parts[1], info);
        case "find":
            return new Command(Command.CommandType.FIND, info);
        case "sort":
            return parseSort(info);
        case "help":
            return new Command(Command.CommandType.HELP);
        default:
            return new Command(Command.CommandType.GIBBERSIH);
        }
    }

    /**
     * Parses a user input to extract the description of a todo task.
     *
     * @param input
     * @return
     * @throws ParserException
     */
    public static Command parseTodo(String input) throws ParserException {
        if (input.trim().isEmpty()) {
            throw new ParserException("Thous thought is incomplete,thou must provide more thought");
        }
        return new Command(Command.CommandType.TODO, input);
    }

    /**
     * Parses a user input to extract the description and deadline of a deadline task.
     *
     * @param input
     * @return
     * @throws ParserException
     */
    public static Command parseDeadline(String input, String info) throws ParserException {
        if (info.isEmpty()) {
            throw new ParserException("Thous thought is incomplete,thou must provide more thought");
        }
        assert input != null : "Unexpected null Input";
        String[] nameAndDeadline = input.split("/by");
        if (nameAndDeadline.length != 2) {
            throw new ParserException("Thoust should try to repeat what you want correctly, see <help>");
        }
        return new Command(Command.CommandType.DEADLINE, nameAndDeadline[0].trim(), nameAndDeadline[1].trim());
    }

    /**
     * Parses a user input to extract the description, start date and end date of an event task.
     *
     * @param input
     * @return
     * @throws ParserException
     */
    public static Command parseEvent(String input, String info) throws ParserException {
        if (info.isEmpty()) {
            throw new ParserException("Thous thought is incomplete,thou must provide more thought");
        }
        assert input != null : "Unexpected null Input";
        String[] infoAndDate = input.split("/");
        if (infoAndDate.length != 3) {
            throw new ParserException("Thoust should try to repeat what you want correctly, see <help>");
        }
        return new Command(Command.CommandType.EVENT, infoAndDate[0].trim(),
                infoAndDate[1].trim(), infoAndDate[2].trim());
    }

    /**
     * Parses a user input to extract the description of a task for the sort command.
     */
    public static Command parseSort(String info) throws ParserException {
        if (info.isEmpty()) {
            throw new ParserException("Thous thought is incomplete,thou must provide more thought");
        }

        if (info.equalsIgnoreCase("a")) {
            return new Command(Command.CommandType.SORT, info);
        } else if (info.equalsIgnoreCase("d")) {
            return new Command(Command.CommandType.SORT, info);
        } else {
            throw new ParserException("Thoust provided an invalid sorting mechanism");
        }
    }

    /**
     * Parses a user input to extract the index of the element for the command to be executed on.
     * Returns the extracted index.
     *
     * @param input single line of user input
     * @return
     * @throws ParserException
     */
    private static int parseIndex(String input) throws ParserException {
        String[] arguments = input.split("\\s+");

        if (arguments.length > 1) {
            throw new ParserException("Thou provided more input than expected");
        } else if (input.isEmpty() || input == null) {
            throw new ParserException("Thou did not provide task number");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ParserException("Thou provided an invalid command");
        }
    }

    /**
     * Returns the task saved in a single lined in a loaded file.
     *
     * @param line line we want to extract the task from.
     * @return
     */
    public static Task getSavedTask(String line) throws DateException {
        String[] parsed = line.split("\\s*\\|\\s*");

        Task task;
        if (parsed[0].equals("T")) {
            task = new ToDo(parsed[2]);
        } else if (parsed[0].equals("D")) {
            task = new Deadline(parsed[2].trim(), parsed[3].trim());
        } else if (parsed[0].equals("E")) {
            task = new Event(parsed[2].trim(), parsed[3].trim(), parsed[4].trim());
        } else {
            return null;
        }

        if (parsed[1].equals("X")) {
            task.setDone(true);
        }

        return task;
    }

}
