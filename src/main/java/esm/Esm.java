package esm;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Entry point for the ESM.
 * Starts and end the session for ESM application by setting up the UI and Storage (loading and saving the file).
 */
public class Esm {

    /**
     * Starts ESM application
     * @param args
     */
    public static void main(String[] args) {

        Path path = Paths.get("data", "esm.txt");
        Storage storage = new Storage(path);
        Ui ui = new Ui();
        TaskList taskList = new TaskList(storage.loadFile());
        storage.saveFile(ui.run(taskList).getList());

    }

}
