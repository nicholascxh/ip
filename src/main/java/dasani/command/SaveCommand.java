package dasani.command;

import dasani.task.TaskList;
import dasani.util.Storage;
import dasani.util.Ui;

/**
 * Represents a command that saves the current task list to storage.
 */
public class SaveCommand extends Command {
    /**
     * Executes the command to save the task list to storage.
     *
     * @param tasks   The task list to be saved.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to save the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        System.out.println("[Dasani]: Task list saved!");
    }
}