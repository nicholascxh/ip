package dasani.command;

import dasani.task.TaskList;
import dasani.util.Ui;
import dasani.util.storage.TaskStorage;
import dasani.exception.DasaniException;

/**
 * Handles deleting tasks from the task list.
 */
public class DeleteCommand extends Command {
    private String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskStorage taskStorage) throws DasaniException {
        tasks.deleteTask(description);
        taskStorage.save(tasks);
    }
}
