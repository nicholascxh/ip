package dasani.util.parser;

import dasani.command.Command;
import dasani.exception.DasaniException;

/**
 * Represents a generic command parser.
 */
public interface CommandParser {
    /**
     * Parses a command description and returns a Command object.
     * @param description The user input excluding the command keyword.
     * @return The corresponding Command object.
     * @throws DasaniException If the command format is invalid.
     */
    Command parse(String description) throws DasaniException;
}
