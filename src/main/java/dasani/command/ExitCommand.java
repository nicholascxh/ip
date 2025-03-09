package dasani.command;

import dasani.task.TaskManager;
import dasani.util.Ui;
import dasani.util.storage.TaskStorage;

/**
 * Handles exiting of Dasani chatbot.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, TaskStorage taskStorage) {
        System.out.println("[Dasani]: Bye! Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
