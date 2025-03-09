package dasani.command;

import dasani.task.TaskList;
import dasani.util.Ui;
import dasani.util.storage.TaskStorage;
import dasani.exception.DasaniException;

/**
 * Represents a command that can be executed in Dasani.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks       The task list to operate on.
     * @param ui          The user interface for displaying messages.
     * @param taskStorage The storage handler for saving/loading tasks.
     * @throws DasaniException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, TaskStorage taskStorage) throws DasaniException;

    /**
     * Determines if the command is an exit command.
     *
     * @return True if the command exits the program, otherwise false.
     */
    public boolean isExit() {
        return false;
    }
}
