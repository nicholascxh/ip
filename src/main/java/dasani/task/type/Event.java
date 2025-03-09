package dasani.task.type;

import dasani.task.Task;
import dasani.exception.InvalidDateException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from        Start time in yyyy-MM-dd HHmm format.
     * @param to          End time in yyyy-MM-dd HHmm format.
     * @throws InvalidDateException If the date format is incorrect.
     */
    public Event(String description, String from, String to) throws InvalidDateException {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Parses a date-time string into a {@code LocalDateTime} object.
     *
     * @param dateTime The date-time string in yyyy-MM-dd HHmm format.
     * @return The parsed {@code LocalDateTime} object.
     * @throws InvalidDateException If the date format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTime) throws InvalidDateException {
        try {
            return LocalDateTime.parse(dateTime.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}
