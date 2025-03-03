package dasani.command;

import dasani.task.TaskList;
import dasani.util.Storage;
import dasani.util.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("[Dasani]: Bye! Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
