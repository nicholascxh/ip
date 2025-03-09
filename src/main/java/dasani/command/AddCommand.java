package dasani.command;

import dasani.task.TaskList;
import dasani.task.type.Deadline;
import dasani.task.type.Event;
import dasani.task.type.Todo;
import dasani.util.Storage;
import dasani.util.Ui;
import dasani.exception.DasaniException;

/**
 * Handles adding tasks to the task list.
 */
public class AddCommand extends Command {
    private String type;
    private String description;

    /**
     * Constructs an AddCommand with a task type and description.
     * @param type Type of task (todo, deadline, event).
     * @param description Task description.
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DasaniException {
        switch (type) {
        case "todo":
            tasks.addTask(new Todo(description));
            break;
        case "deadline":
            String[] deadlineParts = description.split(" /by ", 2);
            tasks.addTask(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            break;
        case "event":
            String[] eventParts = description.split(" /from | /to ", 3);
            tasks.addTask(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
            break;
        default:
            throw new DasaniException("Unknown task type.");
        }
        storage.save(tasks);
    }
}
