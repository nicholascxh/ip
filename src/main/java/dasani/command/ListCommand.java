package dasani.command;

import dasani.task.TaskManager;
import dasani.util.Ui;
import dasani.util.storage.TaskStorage;

/**
 * Handles listing current tasks to the terminal.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, TaskStorage taskStorage) {
        taskManager.displayTasks();
    }
}
