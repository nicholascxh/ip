package dasani.command;

import dasani.task.TaskList;
import dasani.util.Storage;
import dasani.util.Ui;

/**
 * Handles listing current tasks to the terminal.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayTasks();
    }
}
