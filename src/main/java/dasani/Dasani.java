package dasani;

import dasani.exception.DasaniException;
import dasani.task.TaskList;
import dasani.util.Parser;
import dasani.util.Storage;
import dasani.util.Ui;
import dasani.command.Command;

public class Dasani {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Dasani(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DasaniException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DasaniException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Dasani("data/tasks.txt").run();
    }
}
