package dasani.command;

import dasani.task.TaskList;
import dasani.util.Storage;
import dasani.util.Ui;

public class SaveCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        System.out.println("[Dasani]: Task list saved!");
    }
}
