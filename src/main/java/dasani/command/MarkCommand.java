package dasani.command;

import dasani.task.TaskList;
import dasani.util.Storage;
import dasani.util.Ui;
import dasani.exception.DasaniException;

/**
 * Represents a command that marks or unmarks a task in the task list.
 */
public class MarkCommand extends Command {
    private String description;
    private boolean isMark;

    /**
     * Constructs a MarkCommand with a description and a mark status.
     *
     * @param description The description of the task to be marked or unmarked.
     * @param isMark      A boolean indicating whether to mark (true) or unmark (false) the task.
     */
    public MarkCommand(String description, boolean isMark) {
        this.description = description;
        this.isMark = isMark;
    }

    /**
     * Executes the command to mark or unmark a task.
     * Updates the storage after modifying the task list.
     *
     * @param tasks   The task list that contains the tasks.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @throws DasaniException If an error occurs while marking the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DasaniException {
        tasks.markTask(description, isMark);
        storage.save(tasks);
    }
}
