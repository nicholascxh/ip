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
            } else if (command.startsWith("todo")) {
                taskManager.addTask(new Todo(command.substring(5).trim()));
            } else if (command.startsWith("deadline")) {
                String[] parts = command.substring(9).split("/by", 2);
                taskManager.addTask(new Deadline(parts[0].trim(), parts[1].trim()));
            } else if (command.startsWith("event")) {
                String[] parts = command.substring(6).split("/from", 2);
                String[] parts2 = parts[1].split("/to", 2);
                taskManager.addTask(new Event(parts[0].trim(), parts2[0].trim(), parts2[1].trim()));
            } else {
                System.out.println(" 🔵 [Dasani]: Invalid command. Please try again.");
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