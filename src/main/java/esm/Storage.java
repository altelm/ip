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
     * Loads the file containing the saved list from the disk.
     *
     * @return list of tasks (never null)
     * @throws StorageException when an I/O or format error occurs.
     */
    public ArrayList<Task> loadFile() throws StorageException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (this.filePath == null) {
                throw new StorageException("File path is not set");
            }

            if (this.filePath.getParent() != null) {
                Files.createDirectories(this.filePath.getParent());
            }

            if (!Files.exists(this.filePath)) {
                return tasks; // Return an empty list if this is the first time the application is run
            }

            List<String> lines = Files.readAllLines(this.filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line == null || line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                Task task = Parser.getSavedTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }

        } catch (IOException e) {
            throw new StorageException("Error reading file: " + e.getMessage() + " at " + this.filePath);
        } catch (DateException e) {
            throw new StorageException("Invalid date format in saved tasks, please edit the file at " + this.filePath);
        }

        return tasks;
    }

    /**
     * Saves the new list acquired after ending the session with the ESM application into the same file in memory
     *
     * @param tasks arraylist of the new task list
     * @throws StorageException when and I/O error occurs
     */
    public void saveFile(ArrayList<Task> tasks) throws StorageException {

        assert tasks != null : "The list you are trying to save does not exist";
        ArrayList<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            String temp = task.getType() + " | " + task.getDone() + " | " + task.getName();
            if (task instanceof Deadline) {
                temp += " | " + ((Deadline) task).getDeadline();
            } else if (task instanceof Event) {
                temp += " | " + ((Event) task).getStartDate() + " | " + ((Event) task).getEndDate();
            }
            lines.add(temp);
        }

        try {
            if (this.filePath.getParent() != null) {
                Files.createDirectories(this.filePath.getParent());
            }
            Files.write(this.filePath, lines, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("I confess a failure to write tasks to " + this.filePath);
        }
    }
}
