package dasani.command;

import dasani.task.TaskManager;
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
    public void execute(TaskManager taskManager, Ui ui, TaskStorage taskStorage) throws DasaniException {
        taskManager.deleteTask(description);
        taskStorage.save(taskManager.getTaskList());
    }
}
