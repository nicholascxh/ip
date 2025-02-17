package dasani;

import dasani.exception.DasaniException;
import dasani.task.type.Deadline;
import dasani.task.type.Event;
import dasani.task.TaskManager;
import dasani.task.type.Todo;

import java.util.Scanner;

public class Dasani {

    public static void main(String[] args) throws DasaniException {
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

            case "help":
                printHelpMessage();
                break;

            case "list":
                taskManager.displayTasks();
                break;

            case "mark":
                try {
                    taskManager.markTask(description, true);
                } catch (DasaniException e) {
                    Dasani.printLine();
                    System.out.println(e.getMessage());
                    Dasani.printLine();
                }
                break;

            case "unmark":
                try {
                    taskManager.markTask(description, false);
                } catch (DasaniException e) {
                    Dasani.printLine();
                    System.out.println(e.getMessage());
                    Dasani.printLine();
                }
                break;

            case "todo":
                if (!description.isEmpty()) {
                    taskManager.addTask(new Todo(description.trim()));
                } else {
                    System.out.println("[Dasani]: Todo description cannot be empty.");
                }
                break;

            case "deadline":
                try {
                    String[] splitDeadline = description.split("/by", 2);
                    taskManager.addTask(new Deadline(splitDeadline[0].trim(), splitDeadline[1].trim()));
                } catch (Exception e) {
                    System.out.println("[Dasani]: Invalid deadline format. Use: deadline <task> /by <time>");
                }
                break;

            case "event":
                try {
                    String[] splitEvent = description.split("/from", 2);
                    String[] eventPeriod = splitEvent[1].split("/to", 2);
                    taskManager.addTask(new Event(splitEvent[0].trim(), eventPeriod[0].trim(), eventPeriod[1].trim()));
                } catch (Exception e) {
                    System.out.println("[Dasani]: Invalid event format. Use: event <task> /from <start> /to <end>");
                }
                break;

            case "save":
                taskManager.saveTasks();
                System.out.println("[Dasani]: Task list saved!");
                break;

            case "delete":
                try {
                    taskManager.deleteTask(description);
                } catch (DasaniException e) {
                    System.out.println(e.getMessage());
                }
                break;

            default:
                System.out.println("[Dasani]: Invalid command. Type \"help\" to see the list of commands.");
                break;
            }
        }
    }

    private static void printHelpMessage() {
        printLine();
        System.out.println("Not sure what to do? Dont worry! Here are the list of available commands.");
        System.out.println("\"list\" - List all current tasks.");
        System.out.println("\"delete\" - Delete specified task.");
        System.out.println("\"mark\" - Mark task description.");
        System.out.println("\"unmark\" - Unmark task description.");
        System.out.println("\"todo\" - Create a todo task.");
        System.out.println("\"deadline\" - Create a deadline task. E.g Deadline <Task> /by <time>");
        System.out.println("\"event\" - Create an event task. E.g Event <Task> /from <start> /to <end>");
        System.out.println("\"save\" - Save all current tasks.");
        System.out.println("\"bye\" - End this conversation.");
        printLine();
    }

    private static void greetUser() {
        printLine();
        System.out.println("Hello! I'm Dasani, your personal assistant.");
        System.out.println("    What can I do for you today?");
        printLine();
    }

    private static void printFarewellMessage() {
        printLine();
        System.out.println("[Dasani]: Bye! Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("=========================================================");
    }
}