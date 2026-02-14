package esm;

/**
 * Handles the responses to be output for each command
 */
public class Ui {

    /**
     * Returns the response incase and empty input is provided
     *
     * @return
     */
    public String emptyResponse() {
        return ("Words are required, lest thy task vanish into nothingness.");
    }

    /**
     * Returns the response in the case of the unmark command, output depends on the validity of the index
     *
     * @param index
     * @param taskList
     * @return
     */
    public String unmarkResponse(int index, TaskList taskList) {
        if (index == -1) {
            return "Thy ledger is empty";
        } else if (index == -2) {
            return "Thou hath given an non-existeth ledger number";
        }
        return ("It is undone, and so marked in thy ledger:(\n" + taskList.getTask(index));
    }

    /**
     * Returns the response in the case of mark command, output depends on the validity of the index
     *
     * @param index
     * @param taskList
     * @return
     */
    public String markResponse(int index, TaskList taskList) {
        if (index == -1) {
            return "Thy ledger is empty";
        } else if (index == -2) {
            return "Thou hath given an non-existeth ledger number";
        }
        return ("Lo, I have set this task down as finished:)\n" + taskList.getTask(index));
    }

    /**
     * Returns the string format of the list to be printed in the case of a list command
     *
     * @param taskList
     * @return
     */
    public String listResponse(TaskList taskList) {
        return taskList.print();
    }

    /**
     * Returns the response in case of the delete command
     *
     * @param taskList
     * @param tempTask
     * @return
     */
    public String deleteResponse(TaskList taskList, Task tempTask, int index) {
        if (index == -1) {
            return "Thy ledger is empty";
        } else if (index == -2) {
            return "Thou hath given an non-existeth ledger number";
        }
        return ("Thy request is heeded the task is expunged.\n" + tempTask
                + "\nThy ledger now holdeth " + taskList.getSize() + " task(s)");
    }

    /**
     * returns the response incase of task command: todo, deadline, and event
     *
     * @param taskList
     * @param tempTask
     * @return
     */
    public String taskResponse(TaskList taskList, Task tempTask) {
        return ("Aye tis done.\n" + tempTask + "\nThy ledger now holdeth "
                + taskList.getSize() + " task(s)");
    }

    /**
     * Returns the string format of a list containing the tasks found by the find() method in TaskList class
     *
     * @param tasks
     * @return
     */
    public String findTaskResponse(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "No such task was found";
        }
        return tasks.print();
    }

    /**
     * Returns the response in the case of a gibberish input
     *
     * @return
     */
    public String gibberishResponse() {
        return ("I confess myself ignorant of thy intent");
    }

    /**
     * Returns the response when the user asks for help
     *
     * @return
     */
    public String helpReponse() {
        return "To add a todo - todo <name/info>\n"
                + "To add a deadline task - deadline <name/info> /by <date in YYYY-MM-DD\n"
                + "To add an event task - event <name/info> / <start date> / <end date>\n"
                + "To mark a task as done - mark <index of task>\n"
                + "To unmark a task that was done - unmark <index of task>\n"
                + "To delete a task - delete <index of task>\n"
                + "To see a list of all tasks - list\n"
                + "To find a task - find <name/info of task>\n";
    }

    /**
     * Returns the response to bye
     *
     * @return
     */
    public String byeResponse() {
        return ("Fare thee well\n");
    }

    /**
     * Returns a response when all CommandTypes are inapplicable
     *
     * @return
     */
    public String unkownResponse() {
        return ("Seems you find the loophole");
    }
}
