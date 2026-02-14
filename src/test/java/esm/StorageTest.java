package esm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    public void load_fileMissing_returnsEmptyList(){
        Path path = Paths.get("um", "unkown.txt");
        Storage storage = new Storage(path);
        ArrayList<Task> taskList = storage.loadFile();
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void load_filePresent_returnsCorrectList() {
        Path path = Paths.get("data", "esmTest.txt");
        Storage storage = new Storage(path);
        ArrayList<Task> taskList = storage.loadFile();
        assertEquals(1, taskList.size());
    }

    @TempDir
    Path tempDir;

    @Test
    public void save_adjustList_savesCorrectList() {
        Path path = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(path);
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new ToDo("cook"));
        storage.saveFile(taskList);
        taskList = storage.loadFile();
        assertEquals(1, taskList.size());
    }

    /*
    @Test
    public void getTask_validTask_givesCorrectly() {
        String line = "T | X | write a book";
        Task task = Storage.getTask(line);
        assertTrue(task instanceof ToDo);
    }

    public void getTask_invalidTask_givesCorrectly() {
        String line = "G | X | write a book";
        Task task = Storage.getTask(line);
        assertFalse(task instanceof ToDo);
        assertFalse(task instanceof Deadline);
        assertFalse(task instanceof Event);

    }
     */
}
