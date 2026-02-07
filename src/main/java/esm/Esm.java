package esm;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Esm {

    public static void main(String[] args) {

        Path path = Paths.get("data", "esm.txt");
        Storage storage = new Storage(path);
        Ui ui = new Ui();
        TaskList taskList = new TaskList(storage.load());
        storage.save(ui.run(taskList).getList());

    }


}
