package dasani.util;

import java.util.Scanner;

/**
 * Handles user interactions with the console.
 * Provides methods to display messages and read user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a {@code Ui} object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        // Water droplet ASCII Art
        System.out.println("                                                                                                    ");
        System.out.println("                                              .......                                               ");
        System.out.println("                                             :::::::...                                             ");
        System.out.println("                                             ::--::::..                                             ");
        System.out.println("                                             ++++==--+=                                             ");
        System.out.println("                                             =-:.....-+                                             ");
        System.out.println("                                         +::.............:+                                         ");
        System.out.println("                                        +-:..............::=                                        ");
        System.out.println("                                       +-:.................-=                                       ");
        System.out.println("                                      ++-:::............::::=+                                      ");
        System.out.println("                                       %#%**#=*%=##=+#*-#%##%                                       ");
        System.out.println("                                       %#%#+=:-++**-.+*#=###%                                       ");
        System.out.println("                                       %%#*++-+==*=-=++=+*#%%                                       ");
        System.out.println("                                       %%#*===:-=+++-::=++#%%                                       ");
        System.out.println("                                       %%##**+++=====++**##%%                                       ");
        System.out.println("                                       %%%++***##++**#****%%%                                       ");
        System.out.println("                                       *+++++***########*****                                       ");
        System.out.println("                                       #-:.................:*                                       ");
        System.out.println("                                      ++-:::.............:--++                                      ");
        System.out.println("                                      +:::.........  .......:=                                      ");
        System.out.println("                                      =--:.......::.......::-+                                      ");
        System.out.println("                                       *%*:......**......:*%+                                       ");
        System.out.println("                                           --::::   ::::-                                           ");
        System.out.println("                                                                                                    ");
        System.out.println("Hello! I'm Dasani, your personal assistant.");
        System.out.println("What can I do for you today?");
        showLine();
    }

    /**
     * Reads and returns the user's input command.
     *
     * @return The trimmed user command as a string.
     */
    public String readCommand() {
        System.out.print("You: ");
        return scanner.nextLine().trim();
    }

    /**
     * Displays a horizontal line for formatting purposes.
     */
    public void showLine() {
        System.out.println("=========================================================");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("[Dasani]: " + message);
    }

    /**
     * Displays an error message when loading tasks from the file fails.
     */
    public void showLoadingError() {
        System.out.println("[Dasani]: Error loading tasks from file.");
    }

    /**
     * Displays a general message.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
