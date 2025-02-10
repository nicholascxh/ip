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

            String[] words = command.split(" ", 2);
            String keyword = words[0].toLowerCase();
            String description = words.length > 1 ? words[1] : "";

            switch (keyword) {
            case "bye":
                printFarewellMessage();
                input.close();
                return;

            case "list":
                taskManager.displayTasks();
                break;

            case "mark":
                taskManager.markTask(command, true);
                break;

            case "unmark":
                taskManager.markTask(command, false);
                break;

            case "todo":
                if (!description.isEmpty()) {
                    taskManager.addTask(new Todo(description.trim()));
                } else {
                    System.out.println(" 🔵 [Dasani]: Todo description cannot be empty.");
                }
                break;

            case "deadline":
                try {
                    String[] splitDeadline = description.split("/by", 2);
                    taskManager.addTask(new Deadline(splitDeadline[0].trim(), splitDeadline[1].trim()));
                } catch (Exception e) {
                    System.out.println(" 🔵 [Dasani]: Invalid deadline format. Use: deadline <task> /by <time>");
                }
                break;

            case "event":
                try {
                    String[] splitEvent = description.split("/from", 2);
                    String[] eventPeriod = splitEvent[1].split("/to", 2);
                    taskManager.addTask(new Event(splitEvent[0].trim(), eventPeriod[0].trim(), eventPeriod[1].trim()));
                } catch (Exception e) {
                    System.out.println(" 🔵 [Dasani]: Invalid event format. Use: event <task> /from <start> /to <end>");
                }
                break;

            default:
                System.out.println(" 🔵 [Dasani]: Invalid command. Please try again.");
                break;
            }
        }
    }

    private static void greetUser() {
        printLine();
        System.out.println(" 🌊 Hello! I'm Dasani, your personal assistant.");
        System.out.println("    What can I do for you today? 🌟");
        printLine();
    }

    private static void printFarewellMessage() {
        printLine();
        System.out.println(" 🔵 [Dasani]: Bye! Hope to see you again soon! 🌈");
        printLine();
    }

    public static void printLine() {
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}