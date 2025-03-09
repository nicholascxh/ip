package dasani.command;

import dasani.task.TaskList;
import dasani.util.Ui;
import dasani.util.storage.TaskStorage;

/**
 * Handles the find command, which searches for tasks containing a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with a search keyword.
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskStorage taskStorage) {
        tasks.findTasks(keyword);
    }
}
