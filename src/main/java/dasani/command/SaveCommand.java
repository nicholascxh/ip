package dasani.command;

import dasani.task.TaskList;
import dasani.util.Ui;
import dasani.util.storage.TaskStorage;

/**
 * Represents a command that saves the current task list to storage.
 */
public class SaveCommand extends Command {
    /**
     * Executes the command to save the task list to storage.
     *
     * @param tasks       The task list to be saved.
     * @param ui          The user interface to interact with the user.
     * @param taskStorage The storage to save the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStorage taskStorage) {
        taskStorage.save(tasks);
        System.out.println("[Dasani]: Task list saved!");
    }
}
