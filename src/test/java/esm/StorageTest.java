package esm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


public class StorageTest {

    @TempDir
    Path tempDir;

    /**
     * Tests that loadFile returns an empty list when the file does not exist yet.
     */
    @Test
    public void load_fileMissing_returnsEmptyList() throws StorageException {
        Path path = Paths.get("um", "unkown.txt");
        Storage storage = new Storage(path);
        ArrayList<Task> taskList = storage.loadFile();
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests that loadFile loads the correct list when the file is present.
     */
    @Test
    public void load_filePresent_returnsCorrectList() throws StorageException {
        Path path = Paths.get("data", "esmTest.txt");
        Storage storage = new Storage(path);
        ArrayList<Task> taskList = storage.loadFile();
        assertEquals(1, taskList.size());
    }

    /**
     * Tests that saveFile correctly saves the list to the file.
     */
    @Test
    public void save_adjustList_savesCorrectList() throws StorageException {
        Path path = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(path);
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new ToDo("cook"));
        storage.saveFile(taskList);
        taskList = storage.loadFile();
        assertEquals(1, taskList.size());
    }
}
