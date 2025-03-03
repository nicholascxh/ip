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
        String description = words.length > 1 ? words[1] : "";

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
            return new AddCommand("todo", description);
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

    private static AddCommand parseDeadline(String description) throws DasaniException {
        String[] splitDeadline = description.split("/by", 2);
        if (splitDeadline.length < 2) {
            throw new DasaniException("Invalid deadline format. Use: deadline <task> /by yyyy-MM-dd HHmm");
        }
        return new AddCommand("deadline", splitDeadline[0].trim() + " /by " + splitDeadline[1].trim());
    }

    private static AddCommand parseEvent(String description) throws DasaniException {
        String[] splitEvent = description.split("/from", 2);
        if (splitEvent.length < 2 || !splitEvent[1].contains("/to")) {
            throw new DasaniException("Invalid event format. Use: event <task> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        return new AddCommand("event", description);
    }
}