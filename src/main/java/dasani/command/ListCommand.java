package dasani.command;

import dasani.task.TaskList;
import dasani.util.Ui;
import dasani.util.storage.TaskStorage;

/**
 * Handles listing current tasks to the terminal.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStorage taskStorage) {
        tasks.displayTasks();
    }
}
