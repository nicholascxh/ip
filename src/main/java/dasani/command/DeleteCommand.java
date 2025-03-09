package dasani.command;

import dasani.task.TaskList;
import dasani.util.Storage;
import dasani.util.Ui;
import dasani.exception.DasaniException;

/**
 * Handles deleting tasks to the task list.
 */
public class DeleteCommand extends Command {
    private String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DasaniException {
        tasks.deleteTask(description);
        storage.save(tasks);
    }
}
