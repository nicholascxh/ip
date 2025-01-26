import java.util.Scanner;

public class Dasani {

    public static void main(String[] args) {
        // Greeting message with enhancements
        printLine();
        System.out.println(" 🌊 Hello! I'm Dasani, your personal assistant.");
        System.out.println("    What can I do for you today? 🌟");
        printLine();

        // Scanner to read user input
        Scanner input = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print(" You: "); // Prompt for user input
            command = input.nextLine().trim(); // Read and trim the user's input

            if (command.equalsIgnoreCase("bye")) {
                // Exit message
                printLine();
                System.out.println(" 🔵 [Dasani]: Bye! Hope to see you again soon! 🌈");
                printLine();
                break; // Exit the loop
            } else {
                // Echo the user input with enhanced message format
                printLine();
                System.out.println(" 🔵 [Dasani]: You said, \"" + command + "\" 💬");
                printLine();
            }
        }
        input.close(); // Close Scanner
    }

    // Utility method to print a styled border line
    public static void printLine() {
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}