package dasani.util;

import dasani.command.*;
import dasani.exception.DasaniException;

public class Parser {
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
            return new AddCommand("deadline", description);
        case "event":
            return new AddCommand("event", description);
        case "delete":
            return new DeleteCommand(description);
        case "save":
            return new SaveCommand();
        default:
            throw new DasaniException("Invalid command. Type 'help' to see the list of commands.");
        }
    }
}
