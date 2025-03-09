package dasani.util.storage;

import dasani.task.Task;
import dasani.task.type.Deadline;
import dasani.task.type.Event;
import dasani.task.type.Todo;
import dasani.exception.InvalidDateException;

import java.time.format.DateTimeFormatter;

/**
 * Handles parsing tasks to and from storage format.
 */
public class TaskParser {
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a task from a stored file line.
     *
     * @param line The stored task string.
     * @return A Task object or null if invalid.
     */
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null;

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

    /**
     * Converts a task into a savable format.
     *
     * @param task The task to format.
     * @return Formatted string for storage.
     */
    public static String taskToFileFormat(Task task) {
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

