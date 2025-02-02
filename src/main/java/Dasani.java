import java.util.Scanner;

public class Dasani {

    public static void main(String[] args) {
        greetUser();

        TaskManager taskManager = new TaskManager();
        Scanner input = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print(" You: ");
            command = input.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                farewellMessage();
                break;
            } else if (command.equalsIgnoreCase("list")) {
                taskManager.displayTasks();
            } else if (command.startsWith("mark")) {
                taskManager.markTask(command, true);
            } else if (command.startsWith("unmark")) {
                taskManager.markTask(command, false);
            } else {
                taskManager.addTask(command);
            }
        }
        input.close();
    }

    private static void greetUser() {
        printLine();
        System.out.println(" 🌊 Hello! I'm Dasani, your personal assistant.");
        System.out.println("    What can I do for you today? 🌟");
        printLine();
    }

    private static void farewellMessage() {
        printLine();
        System.out.println(" 🔵 [Dasani]: Bye! Hope to see you again soon! 🌈");
        printLine();
    }

    public static void printLine() {
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}