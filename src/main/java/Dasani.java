import java.util.Scanner;

public class Dasani {


    public static void main(String[] args) {
        // Greeting message
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Dasani"); // Replace "Nova" with your chatbot name
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        // Exit message
        Scanner input = new Scanner(System.in);
        String command;

        while (true) {
            command = input.nextLine(); // Read user input

            if (command.equals("bye")) {
                // Exit message
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop and terminate the program
            } else {
                // Echo user input
                System.out.println("____________________________________________________________");
                System.out.println("Echoing: " + command);
                System.out.println("____________________________________________________________");
            }
        }

    }
}
