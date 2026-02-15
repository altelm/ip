package esm;

/**
 * Parses user input to extract required data.
 */
public class Parser {

    /**
     * Parses a single user input to extract the command to be executed
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

        if (parts[0].equalsIgnoreCase("list")) {
            return new Command(Command.CommandType.LIST);
        } else if (parts[0].equalsIgnoreCase("bye")) {
            return new Command(Command.CommandType.BYE);
        } else if (parts[0].equalsIgnoreCase("delete")) {
            return new Command(Command.CommandType.DELETE, parseIndex(info));
        } else if (parts[0].equalsIgnoreCase("mark")) {
            return new Command(Command.CommandType.MARK, parseIndex(info));
        } else if (parts[0].equalsIgnoreCase("unmark")) {
            return new Command(Command.CommandType.UNMARK, parseIndex(info));
        } else if (parts[0].equalsIgnoreCase("todo")) {
            if (info.isEmpty()) {
                throw new ParserException("Thous thought is incomplete,thou must provide more thought");
            }
            return new Command(Command.CommandType.TODO, info);
        } else if (parts[0].equalsIgnoreCase("deadline")) {
            if (info.isEmpty()) {
                throw new ParserException("Thous thought is incomplete,thou must provide more thought");
            }
            assert parts[1] != null : "Unexpected null Input";
            String[] nameAndDeadline = parts[1].split("/by");
            if (nameAndDeadline.length != 2) {
                throw new ParserException("Thoust should try to repeat what you want correctly, see <help>");
            }
            return new Command(Command.CommandType.DEADLINE, nameAndDeadline[0].trim(), nameAndDeadline[1].trim());
        } else if (parts[0].equalsIgnoreCase("event")) {
            if (info.isEmpty()) {
                throw new ParserException("Thous thought is incomplete,thou must provide more thought");
            }
            assert parts[1] != null : "Unexpected null Input";
            String[] infoAndDate = parts[1].split("/");
            if (infoAndDate.length != 3) {
                throw new ParserException("Thoust should try to repeat what you want correctly, see <help>");
            }
            return new Command(Command.CommandType.EVENT, infoAndDate[0].trim(),
                    infoAndDate[1].trim(), infoAndDate[2].trim());
        } else if (parts[0].equalsIgnoreCase("find")) {
            return new Command(Command.CommandType.FIND, info);
        } else if (parts[0].equalsIgnoreCase("sort")) {
            if (info.isEmpty()) {
                throw new ParserException("Thous thought is incomplete,thou must provide more thought");
            }

            if (info.equalsIgnoreCase("a")) {
                return new Command(Command.CommandType.SORT, info);
            } else if (info.equalsIgnoreCase("d")) {
                return new Command(Command.CommandType.SORT, info);
            } else {
                throw new ParserException("Thoust provided invalid sorting mechanism");
            }
        } else if (parts[0].equalsIgnoreCase("help")) {
            return new Command(Command.CommandType.HELP);
        } else {
            return new Command(Command.CommandType.GIBBERSIH);
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
     * Returns the task saved in a single lined in a loaded file
     *
     * @param line line we want to extract the task from
     * @return
     */
    public static Task getSavedTask(String line) throws DateException {
        String[] parsed = line.split("\\s*\\|\\s*");

        Task task;
        if (parsed[0].equals("T")) {
            task = new ToDo(parsed[2]);
        } else if (parsed[0].equals("D")) {
            String[] nameAndDeadline = parsed[2].trim().split("/by");
            task = new Deadline(nameAndDeadline[0].trim(), nameAndDeadline[1].trim());
        } else if (parsed[0].equals("E")) {
            String[] infoAndDate = parsed[2].trim().split("/");
            task = new Event(infoAndDate[0], infoAndDate[1], infoAndDate[2]);
        } else {
            return null;
        }

        if (parsed[1].equals("1")) {
            task.setDone("1");
        }

        return task;
    }

}
