import java.util.Scanner;

public class Dasani {

    public static void main(String[] args) {
        greetUser(); // Display greeting message

        // Store list array
        String[] List = new String[100];

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
                for (int i = 0; List[i] != null; i += 1) {
                    System.out.println(i + 1 + ". " + List[i]);
                }
                printLine();
            } else {
                // Store user input
                int i = 0;
                while (List[i] != null) {
                    i += 1;
                }
                List[i] = command;
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