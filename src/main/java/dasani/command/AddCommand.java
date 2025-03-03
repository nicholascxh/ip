package dasani.command;

import dasani.task.TaskList;
import dasani.util.Storage;
import dasani.util.Ui;
import dasani.exception.DasaniException;

public class AddCommand extends Command {
    private String type;
    private String description;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DasaniException {
        tasks.addTaskFromCommand(type, description);
        storage.save(tasks);
    }
}
