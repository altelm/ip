package esm;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 * Entry point for the ESM.
 * Starts and end the session for ESM application by setting up the UI and Storage (loading and saving the file).
 */
public class Esm {

    private Path path;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a Esm object
     */
    public Esm() {
        this.path = Paths.get("data", "esm.txt");
        this.storage = new Storage(path);
        ArrayList<Task> tempList = storage.loadFile();
        assert tempList != null : "Check your file, file should not be null";
        this.taskList = new TaskList(tempList);
        this.ui = new Ui();

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

        assert command != null : "Command should not be null";
        try {
            switch (command.getType()) {
            case EMPTY:
                return ui.emptyResponse();
            case LIST:
                return ui.listResponse(this.taskList);
            case MARK: {
                taskList.markTask(command.getIndex());
                return ui.markResponse(command.getIndex(), this.taskList);
            }
            case UNMARK: {
                taskList.unmarkTask(command.getIndex());
                return ui.unmarkResponse(command.getIndex(), this.taskList);
            }
            case DELETE: {
                Task tempTask = this.taskList.removeTask(command.getIndex());
                return ui.deleteResponse(this.taskList, tempTask, command.getIndex());
            }
            case TODO: {
                assert command.getInfo() != null && command.getInfo() != "" : "ToDo info is missing";
                Task temptask = new ToDo(command.getInfo());
                taskList.addTask(temptask);
                return ui.taskResponse(this.taskList, temptask);
            }
            case DEADLINE: {
                assert command.getInfo() != null && command.getInfo() != "" : "Deadline info is missing";
                assert command.getDeadline() != null && command.getDeadline() != "" : "Deadline date is missing";
                Task tempTask = new Deadline(command.getInfo(), command.getDeadline());
                taskList.addTask(tempTask);
                return ui.taskResponse(this.taskList, tempTask);
            }
            case EVENT: {
                assert command.getInfo() != null && command.getInfo() != "" : "Event info is missing";
                assert command.getStartDate() != null && command.getStartDate() != "" : "Event start date is missing";
                assert command.getEndDate() != null && command.getEndDate() != "" : "Event end date is missing";
                Task temptask = new Event(command.getInfo(), command.getStartDate(), command.getEndDate());
                taskList.addTask(temptask);
                return ui.taskResponse(this.taskList, temptask);
            }
            case FIND: {
                TaskList tasks = taskList.findTask(command.getInfo());
                return ui.findTaskResponse(tasks);
            }
            case GIBBERSIH:
                return ui.gibberishResponse();
            case BYE: {
                storage.saveFile(taskList.getList());
                return ui.byeResponse();
            }
            case HELP: {
                return ui.helpReponse();
            }
            case SORT: {
                TaskList tempList = this.taskList.sortList(command.getInfo());
                return ui.listResponse(tempList);
            }
            default:
                return ui.unkownResponse();
            }
        } catch (EsmException e) {
            return e.getMessage();
        }
    }
}
