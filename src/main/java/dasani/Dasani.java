package dasani;

import dasani.exception.DasaniException;
import dasani.task.TaskList;
import dasani.util.storage.*;
import dasani.util.*;

import dasani.command.Command;

/**
 * Main class for the Dasani chatbot.
 * Handles initialization, user interactions, and execution loop.
 */
public class Dasani {
    private TaskStorage taskStorage;
    private TaskList tasks;
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
            tasks = new TaskList(taskStorage.load());
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
                c.execute(tasks, ui, taskStorage);
                isExit = c.isExit();
            } catch (DasaniException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Dasani().run();
    }
}
