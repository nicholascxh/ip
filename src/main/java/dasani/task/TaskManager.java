package dasani.task;

import dasani.task.type.Deadline;
import dasani.task.type.Event;
import dasani.task.type.Todo;
import dasani.exception.DasaniException;
import dasani.util.Ui;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages task operations such as adding, deleting, marking, and searching.
 */
public class TaskManager {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a TaskManager with an empty task list.
     */
    public TaskManager() {
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Constructs a TaskManager with pre-loaded tasks.
     *
     * @param loadedTasks List of tasks loaded from storage.
     */
    public TaskManager(List<Task> loadedTasks) {
        this.taskList = new TaskList(loadedTasks);
        this.ui = new Ui();
    }

    /**
     * Returns the TaskList managed by this TaskManager.
     *
     * @return The task list.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.addTask(task);
        ui.showMessage("[Dasani]: Added: \"" + task + "\"");
    }

    /**
     * Parses input and adds a new task.
     *
     * @param type        The type of task (todo, deadline, event).
     * @param description The task description.
     * @throws DasaniException If the input format is incorrect.
     */
    public void addTaskFromCommand(String type, String description) throws DasaniException {
        if (description.isEmpty()) {
            throw new DasaniException("[Dasani]: Task description cannot be empty.");
        }

        switch (type) {
        case "todo":
            addTask(new Todo(description));
            break;
        case "deadline":
            String[] parts = description.split(" /by ", 2);
            if (parts.length < 2) {
                throw new DasaniException("[Dasani]: Invalid deadline format. Use: deadline <task> /by yyyy-MM-dd HHmm");
            }
            addTask(new Deadline(parts[0].trim(), parts[1].trim()));
            break;
        case "event":
            String[] eventParts = description.split(" /from | /to ", 3);
            if (eventParts.length < 3) {
                throw new DasaniException("[Dasani]: Invalid event format. Use: event <task> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
            }
            addTask(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
            break;
        default:
            throw new DasaniException("[Dasani]: Unknown task type.");
        }
    }

    /**
     * Deletes a task based on its task number.
     *
     * @param description The task number.
     * @throws DasaniException If the task number is invalid.
     */
    public void deleteTask(String description) throws DasaniException {
        int taskIndex = parseTaskNumber(description);
        Task removedTask = taskList.removeTask(taskIndex);
        ui.showMessage("[Dasani]: Deleted: \"" + removedTask + "\"");
    }

    /**
     * Marks or unmarks a task as done.
     *
     * @param description The task number.
     * @param markAsDone  Whether to mark or unmark.
     * @throws DasaniException If the task number is invalid.
     */
    public void markTask(String description, boolean markAsDone) throws DasaniException {
        int taskIndex = parseTaskNumber(description);
        Task task = taskList.getTask(taskIndex);

        if (markAsDone) {
            if (task.isDone()) {
                ui.showMessage("[Dasani]: Task " + (taskIndex + 1) + " is already marked as done!");
                return;
            }
            task.markAsDone();
            ui.showMessage("[Dasani]: Marked task as done: " + task);
        } else {
            if (!task.isDone()) {
                ui.showMessage("[Dasani]: Task " + (taskIndex + 1) + " is already unmarked!");
                return;
            }
            task.markAsNotDone();
            ui.showMessage("[Dasani]: Unmarked task: " + task);
        }
    }

    /**
     * Finds tasks containing a given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public void findTasks(String keyword) {
        List<Task> matchingTasks = taskList.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            ui.showMessage("[Dasani]: No matching tasks found.");
        } else {
            ui.showMessage("[Dasani]: Matching tasks:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }

    /**
     * Displays all tasks in the list.
     */
    public void displayTasks() {
        if (taskList.size() == 0) {
            ui.showMessage("[Dasani]: Your task list is empty.");
        } else {
            ui.showMessage("[Dasani]: Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                ui.showMessage((i + 1) + ". " + taskList.getTask(i));
            }
        }
    }

    /**
     * Parses the task number from user input.
     *
     * @param description The user input.
     * @return The zero-based task index.
     * @throws DasaniException If the input is invalid.
     */
    private int parseTaskNumber(String description) throws DasaniException {
        try {
            int taskNumber = Integer.parseInt(description) - 1;
            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                throw new DasaniException("[Dasani]: Invalid task number.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DasaniException("[Dasani]: Please enter a valid task number.");
        }
    }
}
