package esm;

public class Parser {

    public static Command parseInput(String input) throws ParserException {
        if (input == null || input.trim().isEmpty()) {
            return new Command(Command.Type.EMPTY);
        }

        String[] parts = input.trim().split("\\s+", 2);
        String info = (parts.length == 2) ? parts[1].trim() : "";

        if (parts[0].equalsIgnoreCase("list")) {
            return new Command(Command.Type.LIST);
        } else if (parts[0].equalsIgnoreCase("bye")) {
            return new Command(Command.Type.BYE);
        } else if (parts[0].equalsIgnoreCase("delete")) {
            return new Command(Command.Type.DELETE, parseIndex(info));
        } else if (parts[0].equalsIgnoreCase("mark")) {
            return new Command(Command.Type.MARK, parseIndex(info));
        } else if (parts[0].equalsIgnoreCase("unmark")) {
            return new Command(Command.Type.UNMARK, parseIndex(info));
        } else if (parts[0].equalsIgnoreCase("todo")) {
            if (info.isEmpty()) throw new ParserException("Thous thought is incomplete,thou must provide more thought");
            return new Command(Command.Type.TODO, info);
        } else if (parts[0].equalsIgnoreCase("deadline")) {
            if (info.isEmpty()) throw new ParserException("Thous thought is incomplete,thou must provide more thought");
            return new Command(Command.Type.DEADLINE, info);
        } else if (parts[0].equalsIgnoreCase("event")) {
            if (info.isEmpty()) throw new ParserException("Thous thought is incomplete,thou must provide more thought");
            return new Command(Command.Type.EVENT, info);
        } else {
            return new Command(Command.Type.GIBBERISH);
        }
    }

    private static int parseIndex(String input) throws ParserException {
        String[] arguments = input.split("\\s+");
        if(arguments.length > 1) {
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
}
