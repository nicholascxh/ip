package dasani.command;

import dasani.task.TaskList;
import dasani.util.Storage;
import dasani.util.Ui;
import dasani.exception.DasaniException;

public class MarkCommand extends Command {
    private String description;
    private boolean isMark;

    public MarkCommand(String description, boolean isMark) {
        this.description = description;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DasaniException {
        tasks.markTask(description, isMark);
        storage.save(tasks);
    }
}
