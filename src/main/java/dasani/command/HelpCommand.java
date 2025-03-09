package dasani.command;

import dasani.task.TaskManager;
import dasani.util.Ui;
import dasani.util.storage.TaskStorage;

/**
 * Lists and explains all available commands.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, TaskStorage taskStorage) {
        System.out.println("[Dasani]: Here are the available commands:");
        System.out.println("\"list\" - List all current tasks.");
        System.out.println("\"todo <task>\" - Add a todo task.");
        System.out.println("\"deadline <task> /by <time>\" - Add a deadline task.");
        System.out.println("\"event <task> /from <start> /to <end>\" - Add an event task.");
        System.out.println("\"mark <task number>\" - Mark a task as done.");
        System.out.println("\"unmark <task number>\" - Unmark a task.");
        System.out.println("\"delete <task number>\" - Delete a task.");
        System.out.println("\"save\" - Save all current tasks.");
        System.out.println("\"find <keyword>\" - Find all tasks containing a specified keyword.");
        System.out.println("\"bye\" - Exit the program.");
    }
}
