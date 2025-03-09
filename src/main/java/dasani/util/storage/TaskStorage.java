package dasani.util.storage;

import dasani.exception.DasaniException;
import dasani.task.Task;
import dasani.task.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file operations for saving and loading tasks.
 */
public class TaskStorage {
    private final String filePath;

    /**
     * Constructs a TaskStorage instance using the StorageManager.
     *
     * @param storageManager The storage manager to get the file path.
     */
    public TaskStorage(StorageManager storageManager) {
        this.filePath = storageManager.getFilePath();
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks.
     * @throws DasaniException If there is an error reading the file.
     */
    public List<Task> load() throws DasaniException {
        File file = new File(filePath);
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = TaskParser.parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new DasaniException("[Dasani]: Error loading tasks from " + filePath);
        }
        return tasks;
    }

    /**
     * Saves the task list to the storage file.
     *
     * @param taskList The task list to save.
     */
    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTasks()) {
                writer.write(TaskParser.taskToFileFormat(task) + "\n");
            }
            System.out.println("[Dasani]: Tasks saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("[Dasani]: Error saving tasks to file: " + filePath);
            e.printStackTrace();
        }
    }
}

