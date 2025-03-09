package dasani.util.parser;

import dasani.command.AddCommand;
import dasani.exception.DasaniException;

/**
 * Parses task-related commands: todo, deadline, event.
 */
public class TaskCommandParser implements CommandParser {
    private final String taskType;

    public TaskCommandParser(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public AddCommand parse(String description) throws DasaniException {
        if (description.isEmpty()) {
            throw new DasaniException("The description of a task cannot be empty.");
        }

        switch (taskType) {
        case "todo":
            return new AddCommand("todo", description);
        case "deadline":
            return parseDeadline(description);
        case "event":
            return parseEvent(description);
        default:
            throw new DasaniException("Unknown task type.");
        }
    }

    private AddCommand parseDeadline(String description) throws DasaniException {
        String[] parts = description.split(" /by ", 2);
        if (parts.length < 2) {
            throw new DasaniException("Invalid deadline format. Use: deadline <task> /by yyyy-MM-dd HHmm");
        }
        return new AddCommand("deadline", parts[0].trim() + " /by " + parts[1].trim());
    }

    private AddCommand parseEvent(String description) throws DasaniException {
        String[] parts = description.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new DasaniException("Invalid event format. Use: event <task> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        return new AddCommand("event", description);
    }
}
