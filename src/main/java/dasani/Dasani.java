package dasani;

import dasani.exception.DasaniException;
import dasani.task.TaskList;
import dasani.util.Parser;
import dasani.util.Storage;
import dasani.util.Ui;
import dasani.command.Command;

/**
 * Main class for the Dasani chatbot.
 * Handles initialization, user interactions, and execution loop.
 */
public class Dasani {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Dasani instance.
     * Initializes UI, storage, and loads tasks from the specified file.
     *
     * @param filePath The path to the file where tasks are stored.
     */
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

    /**
     * Runs the chatbot, processing user commands in a loop until exit command is given.
     */
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

    /**
     * The main entry point of the program.
     *
     * @param args Command line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        new Dasani("data/tasks.txt").run();
    }
}