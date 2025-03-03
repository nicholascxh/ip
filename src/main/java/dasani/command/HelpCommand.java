package dasani.command;

import dasani.task.TaskList;
import dasani.util.Storage;
import dasani.util.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("[Dasani]: Here are the available commands:");
        System.out.println("\"list\" - List all current tasks.");
        System.out.println("\"todo <task>\" - Add a todo task.");
        System.out.println("\"deadline <task> /by <time>\" - Add a deadline task.");
        System.out.println("\"event <task> /from <start> /to <end>\" - Add an event task.");
        System.out.println("\"mark <task number>\" - Mark a task as done.");
        System.out.println("\"unmark <task number>\" - Unmark a task.");
        System.out.println("\"delete <task number>\" - Delete a task.");
        System.out.println("\"save\" - Save all current tasks.");
        System.out.println("\"help\" - Show this help message.");
        System.out.println("\"bye\" - Exit the program.");
    }
}
