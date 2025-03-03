package dasani.util;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Dasani, your personal assistant.");
        System.out.println("What can I do for you today?");
        showLine();
    }

    public String readCommand() {
        System.out.print("You: ");
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("=========================================================");
    }

    public void showError(String message) {
        System.out.println("[Dasani]: " + message);
    }

    public void showLoadingError() {
        System.out.println("[Dasani]: Error loading tasks from file.");
    }
}
