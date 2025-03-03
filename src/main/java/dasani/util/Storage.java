package dasani.util;

import dasani.task.Task;
import dasani.task.TaskList;
import dasani.task.type.Deadline;
import dasani.task.type.Event;
import dasani.task.type.Todo;
import dasani.exception.DasaniException;
import dasani.exception.InvalidDateException;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file operations for saving and loading tasks.
 */
public class Storage {
    private String filePath;
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DasaniException {
        File file = new File(filePath);
        List<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            return tasks;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new DasaniException("Error loading tasks from file.");
        }
        return tasks;
    }

    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTasks()) {
                writer.write(taskToFileFormat(task) + "\n");
            }
        } catch (IOException e) {
            System.out.println("[Dasani]: Error saving tasks.");
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;

        try {
            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                return null;
            }
        } catch (InvalidDateException e) {
            System.out.println("[Dasani]: Skipping task due to invalid date format: " + line);
            return null;
        }

        if (isDone) task.markAsDone();
        return task;
    }

    private String taskToFileFormat(Task task) {
        String type = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
        String status = task.isDone() ? "1" : "0";
        String description = task.getDescription();

        if (task instanceof Deadline) {
            return type + " | " + status + " | " + description + " | " + ((Deadline) task).getBy().format(SAVE_FORMAT);
        } else if (task instanceof Event) {
            return type + " | " + status + " | " + description + " | "
                    + ((Event) task).getFrom().format(SAVE_FORMAT) + " | "
                    + ((Event) task).getTo().format(SAVE_FORMAT);
        } else {
            return type + " | " + status + " | " + description;
        }
    }
}
