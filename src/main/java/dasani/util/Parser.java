package dasani.util;

import dasani.command.*;
import dasani.exception.DasaniException;
import dasani.util.parser.CommandParser;
import dasani.util.parser.TaskCommandParser;

import java.util.HashMap;
import java.util.Map;

/**
 * The Parser class handles user input parsing and delegates command parsing to specific handlers.
 */
public class Parser {
    private static final Map<String, CommandParser> commandParsers = new HashMap<>();

    // Initialize command parsers
    static {
        commandParsers.put("todo", new TaskCommandParser("todo"));
        commandParsers.put("deadline", new TaskCommandParser("deadline"));
        commandParsers.put("event", new TaskCommandParser("event"));
    }

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
        case "delete":
            return new DeleteCommand(description);
        case "find":
            return new FindCommand(description);
        case "save":
            return new SaveCommand();
        default:
            // Delegate parsing for task-related commands
            if (commandParsers.containsKey(keyword)) {
                return commandParsers.get(keyword).parse(description);
            }
            throw new DasaniException("Invalid command. Type 'help' to see the list of commands.");
        }
    }
}
