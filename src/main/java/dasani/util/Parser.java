package dasani.util;

import dasani.command.*;
import dasani.exception.DasaniException;
import dasani.exception.InvalidDateException;

/**
 * The Parser class handles user input parsing and converts commands into executable objects.
 * Parses user input into corresponding commands.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput The full user command as a string.
     * @return The corresponding Command object.
     * @throws DasaniException If the command is invalid or unrecognized.
     */
    public static Command parse(String userInput) throws DasaniException {
        String[] words = userInput.split(" ", 2);
        String keyword = words[0].toLowerCase();
        String description = words.length > 1 ? words[1].trim() : "";

        switch (keyword) {
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(description, true);
        case "unmark":
            return new MarkCommand(description, false);
        case "todo":
            return parseTodo(description);
        case "deadline":
            return parseDeadline(description);
        case "event":
            return parseEvent(description);
        case "delete":
            return new DeleteCommand(description);
        case "find":
            return new FindCommand(description);
        case "save":
            return new SaveCommand();
        default:
            throw new DasaniException("Invalid command. Type 'help' to see the list of commands.");
        }
    }

    /**
     * Parses the todo command, ensuring that the description is not empty.
     *
     * @param description The description of the todo task.
     * @return An AddCommand representing the todo task.
     * @throws DasaniException If the description is empty.
     */
    private static AddCommand parseTodo(String description) throws DasaniException {
        if (description.isEmpty()) {
            throw new DasaniException("The description of a todo cannot be empty.");
        }

        return new AddCommand("todo", description);
    }

    /**
     * Parses the deadline command, ensuring the correct format.
     *
     * @param description The description containing the task and deadline time.
     * @return An AddCommand representing the deadline task.
     * @throws DasaniException If the format is incorrect or the description is missing.
     */
    private static AddCommand parseDeadline(String description) throws DasaniException {
        String[] deadlineParts = description.split("/by", 2);
        if (deadlineParts[0].isEmpty()) {
            throw new DasaniException("The description of a todo cannot be empty.");
        }
        if (deadlineParts.length < 2) {
            throw new DasaniException("Invalid deadline format. Use: deadline <task> /by yyyy-MM-dd HHmm");
        }

        return new AddCommand("deadline", deadlineParts[0].trim() + " /by " + deadlineParts[1].trim());
    }

    /**
     * Parses the event command, ensuring the correct format.
     *
     * @param description The description containing the task, start time, and end time.
     * @return An AddCommand representing the event task.
     * @throws DasaniException If the format is incorrect or the required parameters are missing.
     */
    private static AddCommand parseEvent(String description) throws DasaniException {
        String[] eventParts = description.split(" /from | /to ", 3);
        if (eventParts[0].isEmpty()) {
            throw new DasaniException("The description of a todo cannot be empty.");
        }
        if (eventParts.length < 3) {
            throw new DasaniException("Invalid event format. Use: event <task> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }

        return new AddCommand("event", description);
    }
}
