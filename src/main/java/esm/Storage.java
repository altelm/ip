package esm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a storage mechansim for the list used by the ESM application
 */
public class Storage {

    private Path filePath;

    /**
     * Creates a path object with the specified source
     *
     * @param filePath
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the task saved in a single lined in the loaded file
     *
     * @param line line we want to extract the task from
     * @return
     */
    public static Task getTask(String line) {

        String[] parsed = line.split("\\s*\\|\\s*");

        Task task;
        if (parsed[0].equals("T")) {
            task = new ToDo(parsed[2]);
        } else if (parsed[0].equals("D")) {
            task = new Deadline(parsed[2].trim().split("\\s+"));
        } else if (parsed[0].equals("E")) {
            task = new Event(parsed[2].trim().split("\\s+"));
        } else {
            return null;
        }

        if (parsed[1].equals("1")) {
            task.setDone("1");
        }

        return task;
    }

    /**
     * Loads the file containing the saved list from the disk
     *
     * @return
     */
    public ArrayList<Task> loadFile() {
        ArrayList<Task> tasks = new ArrayList<>();


        if (!Files.exists(this.filePath)) {
            return tasks;
        }

        try {
            if (this.filePath.getParent() != null) {
                Files.createDirectories(this.filePath.getParent());
            }
        } catch (IOException e) {
            System.out.println("Could not create data folder");
        }

        try {
            List<String> lines = Files.readAllLines(this.filePath);
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                Task task = getTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }

        } catch (IOException e) {
            return tasks;
        }

        return tasks;
    }

    /**
     * Saves the new list acquired after ending the session with ESM application into the same file in memory
     *
     * @param tasks arraylist of the new task list
     */
    public void saveFile(ArrayList<Task> tasks) {

        ArrayList<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            String temp = task.getType() + " | " + task.getDone() + " | " + task.getName();
            lines.add(temp);
        }

        try {
            if (this.filePath.getParent() != null) {
                Files.createDirectories(this.filePath.getParent());
            }
            Files.write(this.filePath, lines, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Cannot save file properly");
        }

    }
}
