package esm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Parser class.
 */
public class ParserTest {

    /**
     * Tests the parseIndex method when the input contains the index of the task to be marked.
     */
    @Test
    public void parseIndex_validInput_success() throws ParserException {
        String input = "mark 2";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.MARK, command.getType());
        assertEquals(2, command.getIndex());
    }

    /**
     * Tests the parseIndex method when the input does not contain a valid index.
     */
    @Test
    public void parseIndex_invalidInput_throwsParserException() {
        String input = "mark two";
        try {
            Parser.parseInput(input);
        } catch (ParserException e) {
            assertTrue(e.getMessage().contains("Thou provided an invalid command"));
        }
    }

    /**
     * Tests the parseIndex method when the input contains more than one index.
     */
    @Test
    public void parseIndex_multipleIndices_throwsParserException() {
        String input = "mark 2 3";
        try {
            Parser.parseInput(input);
        } catch (ParserException e) {
            assertTrue(e.getMessage().contains("Thou provided more input than expected"));
        }
    }

    /**
     * Tests the parseIndex method when the input does not contain any index.
     */
    @Test
    public void parseIndex_noIndex_throwsParserException() {
        String input = " ";
        try {
            Parser.parseInput(input);
        } catch (ParserException e) {
            assertTrue(e.getMessage().contains("Thou did not provide task number"));
        }
    }

    /**
     * Tests the getSavedTask method when the input line contains a saved ToDo task.
     */
    @Test
    public void getSavedTask_validToDo_success() throws DateException {
        String line = "T | X | read book";
        Task task = Parser.getSavedTask(line);
        assertTrue(task instanceof ToDo);
        assertEquals("read book", task.getName());
        assertTrue(task.getDone().equals("X"));
    }

    /**
     * Tets the getSavedTask method when the input line contains a saved Deadline task.
     */
    @Test
    public void getSavedTask_validDeadline_success() throws DateException {
        String line = "D |  | submit assignment | 2024-09-30";
        Task task = Parser.getSavedTask(line);
        assertTrue(task instanceof Deadline);
        assertEquals("submit assignment", task.getName());
        assertEquals("2024-09-30", ((Deadline) task).getDeadline());
    }

    /**
     * Tests the getSavedTask method when the input line contains a saved Event task.
     */
    @Test
    public void getSavedTask_validEvent_success() throws DateException {
        String line = "E |  | project meeting | 2024-10-01 | 2024-10-02";
        Task task = Parser.getSavedTask(line);
        assertTrue(task instanceof Event);
        assertEquals("project meeting", task.getName());
        assertEquals("2024-10-01", ((Event) task).getStartDate());
        assertEquals("2024-10-02", ((Event) task).getEndDate());
    }

    /**
     * Tests the getSavedTask method when the input line does not contain a valid task type.
     */
    @Test
    public void getSavedTask_invalidTaskType_returnsNull() throws DateException {
        String line = "X |  | invalid task";
        Task task = Parser.getSavedTask(line);
        assertEquals(null, task);
    }

    /**
     * Test the getSavedTask method when the input line is empty.
     */
    @Test
    public void getSavedTask_emptyLine_returnsNull() throws DateException {
        String line = "";
        Task task = Parser.getSavedTask(line);
        assertEquals(null, task);
    }

    /**
     * Tests the parseInput method when the input is a valid Deadline command.
     */
    @Test
    public void parseInput_validDeadlineCommand_success() throws ParserException {
        String input = "deadline submit assignment /by 2024-09-30";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.DEADLINE, command.getType());
        assertEquals("submit assignment", command.getInfo());
        assertEquals("2024-09-30", command.getDeadline());
    }

    /**
     * Tests the parseInput method when the input is a valid Event command.
     */
    @Test
    public void parseInput_validEventCommand_success() throws ParserException {
        String input = "event project meeting / 2024-10-01 / 2024-10-02";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.EVENT, command.getType());
        assertEquals("project meeting", command.getInfo());
        assertEquals("2024-10-01", command.getStartDate());
        assertEquals("2024-10-02", command.getEndDate());
    }

    /**
     * Tests the parseInput method when the input is a valid ToDo command.
     */
    @Test
    public void parseInput_validToDoCommand_success() throws ParserException {
        String input = "todo read book";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.TODO, command.getType());
        assertEquals("read book", command.getInfo());
    }

    /**
     * Tests the parseInput method when the input is a valid Mark command.
     */
    @Test
    public void parseInput_validMarkCommand_success() throws ParserException {
        String input = "mark 2";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.MARK, command.getType());
        assertEquals(2, command.getIndex());
    }

    /**
     * Tests the parseInput method when the input is a valid Unmark command.
     */
    @Test
    public void parseInput_validUnmarkCommand_success() throws ParserException {
        String input = "unmark 3";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.UNMARK, command.getType());
        assertEquals(3, command.getIndex());
    }

    /**
     * Tests the parseInput method when the input is a valid Delete command.
     */
    @Test
    public void parseInput_validDeleteCommand_success() throws ParserException {
        String input = "delete 1";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.DELETE, command.getType());
        assertEquals(1, command.getIndex());
    }

    /**
     * Tests the parseInput method when the input is a valid Find command.
     */
    @Test
    public void parseInput_validFindCommand_success() throws ParserException {
        String input = "find book";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.FIND, command.getType());
        assertEquals("book", command.getInfo());
    }

    /**
     * Tests the parseInput method when the input is a valid Sort command with alphabetical order.
     */
    @Test
    public void parseInput_validSortAscendingCommand_success() throws ParserException {
        String input = "sort a";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.SORT, command.getType());
        assertEquals("a", command.getInfo());
    }

    /**
     * Tests the parseInput method when the input is a valid Sort command with nearing deadline order.
     */
    @Test
    public void parseInput_validSortDescendingCommand_success() throws ParserException {
        String input = "sort d";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.SORT, command.getType());
        assertEquals("d", command.getInfo());
    }

    /**
     * Tests the parseInput method when the input is a valid List command.
     */
    @Test
    public void parseInput_validListCommand_success() throws ParserException {
        String input = "list";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.LIST, command.getType());
    }

    /**
     * Tests the parseInput method when the input is a valid Bye command.
     */
    @Test
    public void parseInput_validByeCommand_success() throws ParserException {
        String input = "bye";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.BYE, command.getType());
    }

    /**
     * Tests the parseInput method when the input is empty.
     */
    @Test
    public void parseInput_emptyInput_success() throws ParserException {
        String input = "   ";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.EMPTY, command.getType());
    }

    /**
     * Tests the parseInput method when the input is an unknown string.
     */
    @Test
    public void parseInput_invalidCommand_success() throws ParserException {
        String input = "gibberish";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.GIBBERSIH, command.getType());
    }

    /**
     * Tests the parseInput method when the input is a valid Help command.
     */
    @Test
    public void parseInput_validHelpCommand_success() throws ParserException {
        String input = "help";
        Command command = Parser.parseInput(input);
        assertEquals(Command.CommandType.HELP, command.getType());
    }

    /**
     * Tests the parseInput method when the input is a valid Sort command with invalid sorting mechanism.
     */
    @Test
    public void parseInput_invalidSortMechanism_throwsParserException() {
        String input = "sort x";
        try {
            Parser.parseInput(input);
        } catch (ParserException e) {
            assertTrue(e.getMessage().contains("Thoust provided an invalid sorting mechanism"));
        }
    }

    /**
     * Tests the parseInput method when the input is a valid Deadline command with missing deadline.
     */
    @Test
    public void parseInput_deadlineCommandMissingDeadline_throwsParserException() {
        String input = "deadline submit assignment /by";
        try {
            Parser.parseInput(input);
        } catch (ParserException e) {
            assertTrue(e.getMessage().contains("Thoust should try to repeat what you want correctly, see <help>"));
        }
    }

    /**
     * Tests the parseInput method when the input is an invalid Deadline command.
     */
    @Test
    public void parseInput_invalidDeadlineCommand_throwsParserException() {
        String input1 = "deadline submit assignment by 2026-02-03";
        String input2 = "deadline submit assignment /by by 2026-02-03";
        String input3 = "deadline submit assignment /by 2026-02-03 extra";
        String input4 = "deadline submit assignment /by 2026/04/02";
        try {
            Parser.parseInput(input1);
            Parser.parseInput(input2);
            Parser.parseInput(input3);
            Parser.parseInput(input4);
        } catch (ParserException e) {
            assertTrue(e.getMessage().contains("Thoust should try to repeat what you want correctly, see <help>"));
        }
    }


    /**
     * Tests the parseInput method when the input is a valid Event command with missing dates.
     */
    @Test
    public void parseInput_eventCommandMissingDates_throwsParserException() {
        String input = "event project meeting / 2024-10-01";
        try {
            Parser.parseInput(input);
        } catch (ParserException e) {
            assertTrue(e.getMessage().contains("Thoust should try to repeat what you want correctly, see <help>"));
        }
    }

    /**
     * Tests the parseInput method when the input is an invalid Event command.
     */
    @Test
    public void parseInput_invalidEventCommand_throwsParserException() {
        String input1 = "event project meeting 2024-10-01 2024-10-02";
        String input2 = "event project meeting / 2024-10-01 2024-10-02";
        String input3 = "event project meeting / 2024-10-01 / 2024/10/02";
        try {
            Parser.parseInput(input1);
            Parser.parseInput(input2);
            Parser.parseInput(input3);
        } catch (ParserException e) {
            assertTrue(e.getMessage().contains("Thoust should try to repeat what you want correctly, see <help>"));
        }
    }

    /**
     * Tests the parseInput method when the input is a valid ToDo command with missing description.
     */
    @Test
    public void parseInput_todoCommandMissingDescription_throwsParserException() {
        String input = "todo";
        try {
            Parser.parseInput(input);
        } catch (ParserException e) {
            assertTrue(e.getMessage().contains("Thous thought is incomplete,thou must provide more thought"));
        }
    }
}

