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
     * @param description Description of the event.
     * @param from Start time in yyyy-MM-dd HHmm format.
     * @param to End time in yyyy-MM-dd HHmm format.
     * @throws InvalidDateException If the date format is incorrect.
     */
    public Event(String description, String from, String to) throws InvalidDateException {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTime) throws InvalidDateException {
        try {
            return LocalDateTime.parse(dateTime.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}