import java.util.Scanner;

public class Dasani {

    public static void main(String[] args) {
        greetUser(); // Display greeting message

        Task[] tasks = new Task[100];

        // Scanner to read user input
        Scanner input = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print(" You: "); // Prompt for user input
            command = input.nextLine().trim(); // Read and trim the user's input

            if (command.equalsIgnoreCase("bye")) {
                // Exit when user types 'bye'
                farewellMessage();
                break;
            }
            else if (command.equalsIgnoreCase("list")) {
                // Print stored list to user
                printLine();
                System.out.println(" 🔵 [Dasani]: The list is:");
                for (int i = 0; tasks[i] != null; i += 1) {
                    System.out.println((i + 1) + ". " + tasks[i].toString());
                }
                printLine();
            }
            else if (command.startsWith("mark")) {
                int taskNumber = Integer.parseInt(command.substring(5)) - 1;
                if (tasks[taskNumber] != null) {
                    if (tasks[taskNumber].isMark()) {
                        printLine();
                        System.out.println(" 🔵 [Dasani]: Task: '" + (taskNumber + 1) + ". " + tasks[taskNumber].toString() + "' is already done. ✅");
                        printLine();
                    }
                    else {
                        tasks[taskNumber].markAsDone();
                        printLine();
                        System.out.println(" 🔵 [Dasani]: Okay! Marked task: '" + (taskNumber + 1) + ". " + tasks[taskNumber].toString() + "' as done. ✅");
                        printLine();
                    }
                }
                else {
                    printLine();
                    System.out.println(" 🔵 [Dasani]: Task does not exist. ❌");
                    printLine();
                }
            }
            else if (command.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(command.substring(7)) - 1;
                if (tasks[taskNumber] != null) {
                    if (!tasks[taskNumber].isMark()) {
                        printLine();
                        System.out.println(" 🔵 [Dasani]: Task: '" + (taskNumber + 1) + ". " + tasks[taskNumber].toString() + "' is already not done. 🔄");
                        printLine();
                    }
                    else {
                        tasks[taskNumber].markAsNotDone();
                        printLine();
                        System.out.println(" 🔵 [Dasani]: Okay! Marked task: '" + (taskNumber + 1) + ". " + tasks[taskNumber].toString() + "' as not done. 🔄");
                        printLine();
                    }
                }
                else {
                    printLine();
                    System.out.println(" 🔵 [Dasani]: Task does not exist. ❌");
                    printLine();
                }
            }
            else {
                // Store user input
                int i = 0;
                while (tasks[i] != null) {
                    i += 1;
                }
                tasks[i] = new Task(command);
                printLine();
                System.out.println(" 🔵 [Dasani]: Added: \"" + command + "\" 💬");
                printLine();
            }
        }
        input.close(); // Close Scanner
    }

    // Method to print a greeting message
    private static void greetUser() {
        printLine();
        System.out.println(" 🌊 Hello! I'm Dasani, your personal assistant.");
        System.out.println("    What can I do for you today? 🌟");
        printLine();
    }

    // Method to print a farewell message
    private static void farewellMessage() {
        printLine();
        System.out.println(" 🔵 [Dasani]: Bye! Hope to see you again soon! 🌈");
        printLine();
    }

    // Print a styled border line
    public static void printLine() {
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}