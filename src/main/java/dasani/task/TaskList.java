package dasani.task;

import dasani.task.type.Deadline;
import dasani.task.type.Event;
import dasani.task.type.Todo;
import dasani.exception.DasaniException;
import dasani.util.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks, including adding, deleting, marking, and searching tasks.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Ui ui;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Constructs a TaskList with pre-loaded tasks.
     *
     * @param loadedTasks List of tasks loaded from storage.
     */
    public TaskList(List<Task> loadedTasks) {
        this.tasks = new ArrayList<>(loadedTasks);
        this.ui = new Ui();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("[Dasani]: Added: \"" + task + "\"");
    }

    /**
     * Adds a task based on user input.
     *
     * @param type Type of task (todo, deadline, event).
     * @param description Description of the task.
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
            String[] splitDeadline = description.split("/by", 2);
            if (splitDeadline.length < 2) {
                throw new DasaniException("[Dasani]: Invalid deadline format. Use: deadline <task> /by <time>");
            }
            addTask(new Deadline(splitDeadline[0].trim(), splitDeadline[1].trim()));
            break;
        case "event":
            String[] splitEvent = description.split("/from", 2);
            if (splitEvent.length < 2 || !splitEvent[1].contains("/to")) {
                throw new DasaniException("[Dasani]: Invalid event format. Use: event <task> /from <start> /to <end>");
            }
            String[] eventPeriod = splitEvent[1].split("/to", 2);
            addTask(new Event(splitEvent[0].trim(), eventPeriod[0].trim(), eventPeriod[1].trim()));
            break;
        default:
            throw new DasaniException("[Dasani]: Unknown task type.");
        }
    }

    /**
     * Deletes a task based on its task number.
     *
     * @param description The task number as a string.
     * @throws DasaniException If the task number is invalid.
     */
    public void deleteTask(String description) throws DasaniException {
        if (description.isEmpty()) {
            throw new DasaniException("[Dasani]: Please provide a task number after 'delete'");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DasaniException("[Dasani]: Please enter a valid task number.");
        }

        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new DasaniException("[Dasani]: Task number needs to be between 1 and " + tasks.size());
        }

        Task removedTask = tasks.remove(taskNumber - 1);
        System.out.println("[Dasani]: Deleted: \"" + removedTask + "\"");
    }

    /**
     * Marks or unmarks a task as done.
     *
     * @param description The task number as a string.
     * @param markAsDone True to mark, false to unmark.
     * @throws DasaniException If the task number is invalid.
     */
    public void markTask(String description, boolean markAsDone) throws DasaniException {
        if (description.isEmpty()) {
            throw new DasaniException("[Dasani]: Please provide a task number after 'mark'.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DasaniException("[Dasani]: Please enter a valid task number.");
        }

        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new DasaniException("[Dasani]: Task number needs to be between 1 and " + tasks.size());
        }

        Task task = tasks.get(taskNumber - 1);
        if (markAsDone) {
            if (task.isDone()) {
                System.out.println("[Dasani]: Task " + taskNumber + " is already marked as done!");
                return;
            }
            task.markAsDone();
            System.out.println("[Dasani]: Marked task as done: " + task);
        } else {
            if (!task.isDone()) {
                System.out.println("[Dasani]: Task " + taskNumber + " is already unmarked!");
                return;
            }
            task.markAsNotDone();
            System.out.println("[Dasani]: Unmarked task: " + task);
        }
    }

    /**
     * Searches for tasks containing the given keyword and prints matching results.
     * @param keyword The keyword to search for.
     */
    public void findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println("[Dasani]: No matching tasks found.");
        } else {
            System.out.println("[Dasani]: Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
  
    /**
     * Displays all tasks in the list.
     */
  
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("[Dasani]: Your task list is empty. Add some tasks!");
        } else {
            System.out.println("[Dasani]: The list is:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}