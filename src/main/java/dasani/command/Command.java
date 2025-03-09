package dasani.command;

import dasani.task.TaskList;
import dasani.util.Storage;
import dasani.util.Ui;
import dasani.exception.DasaniException;

/**
 * Abstract command class.
 */

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DasaniException;
    public boolean isExit() {
        return false;
    }
}
