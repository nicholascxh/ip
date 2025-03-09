package dasani.command;

import dasani.task.TaskManager;
import dasani.task.type.Deadline;
import dasani.task.type.Event;
import dasani.task.type.Todo;
import dasani.util.Ui;
import dasani.util.storage.TaskStorage;
import dasani.exception.DasaniException;

/**
 * Handles adding tasks to the task list.
 */
public class AddCommand extends Command {
    private String type;
    private String description;

    /**
     * Constructs an AddCommand with a task type and description.
     *
     * @param type        Type of task (todo, deadline, event).
     * @param description Task description.
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param taskManager       The task list to add the task to.
     * @param ui          The user interface for displaying messages.
     * @param taskStorage The storage handler to save the task.
     * @throws DasaniException If the command format is invalid.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, TaskStorage taskStorage) throws DasaniException {
        switch (type) {
        case "todo":
            taskManager.addTask(new Todo(description));
            break;
        case "deadline":
            String[] deadlineParts = description.split(" /by ", 2);
            if (deadlineParts.length < 2) {
                throw new DasaniException("Invalid deadline format. Use: deadline <task> /by yyyy-MM-dd HHmm");
            }
            taskManager.addTask(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            break;
        case "event":
            String[] eventParts = description.split(" /from | /to ", 3);
            if (eventParts.length < 3) {
                throw new DasaniException("Invalid event format. Use: event <task> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
            }
            taskManager.addTask(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
            break;
        default:
            throw new DasaniException("Unknown task type.");
        }

        // Save updated task list
        taskStorage.save(taskManager.getTaskList());
        System.out.println("[Dasani]: Task added successfully!");
    }
}
