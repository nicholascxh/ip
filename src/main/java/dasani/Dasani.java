package dasani;

import dasani.exception.DasaniException;
import dasani.task.TaskManager;
import dasani.util.storage.*;
import dasani.util.*;
import dasani.command.Command;

/**
 * Main class for the Dasani chatbot.
 * Handles initialization, user interactions, and execution loop.
 */
public class Dasani {
    private TaskStorage taskStorage;
    private TaskManager taskManager; // Updated to use TaskManager instead of TaskList
    private Ui ui;

    /**
     * Constructs a Dasani instance.
     * Initializes UI, storage, and loads tasks.
     */
    public Dasani() {
        ui = new Ui();
        StorageManager storageManager = new StorageManager(); // Manages file setup
        taskStorage = new TaskStorage(storageManager); // Loads and saves tasks

        try {
            taskManager = new TaskManager(taskStorage.load()); // TaskManager now manages tasks
        } catch (DasaniException e) {
            ui.showLoadingError();
            taskManager = new TaskManager();
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
                c.execute(taskManager, ui, taskStorage); // Pass taskManager instead of TaskList
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
        new Dasani().run();
    }
}
