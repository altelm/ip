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
    private Ui ui;

    /**
     * Creates a Esm object
     */
    public Esm() {
        this.path = Paths.get("data", "esm.txt");
        this.storage = new Storage(path);
        this.taskList = new TaskList(storage.loadFile());
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

        try {
            switch (command.getType()) {
            case EMPTY:
                return ui.emptyResponse();
            case LIST:
                return ui.listResponse(this.taskList);
            case MARK: {
                taskList.mark(command.getIndex());
                return ui.markResponse(command.getIndex(), this.taskList);
            }
            case UNMARK: {
                taskList.unmark(command.getIndex());
                return ui.unmarkResponse(command.getIndex(), this.taskList);
            }
            case DELETE: {
                Task tempTask = this.taskList.remove(command.getIndex());
                return ui.deleteResponse(this.taskList, tempTask, command.getIndex());
            }
            case TODO: {
                Task temptask = new ToDo(command.getInfo());
                taskList.add(temptask);
                return ui.taskResponse(this.taskList, temptask);
            }
            case DEADLINE: {
                Task tempTask = new Deadline(command.getInfo(), command.getDeadline());
                taskList.add(tempTask);
                return ui.taskResponse(this.taskList, tempTask);
            }
            case EVENT: {
                Task temptask = new Event(command.getInfo(), command.getStartDate(), command.getEndDate());
                taskList.add(temptask);
                return ui.taskResponse(this.taskList, temptask);
            }
            case FIND: {
                TaskList tasks = taskList.find(command.getInfo());
                return ui.findResponse(tasks);
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
            default:
                return ui.unkownResponse();
            }
        } catch (EsmException e) {
            return e.getMessage();
        }
    }
}
