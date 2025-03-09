package dasani.task.type;

import dasani.task.Task;
import dasani.exception.InvalidDateException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Stores the deadline as a {@code LocalDateTime} object.
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a {@code Deadline} task with a description and a deadline date.
     *
     * @param description The description of the task.
     * @param by          The deadline in yyyy-MM-dd HHmm format.
     * @throws InvalidDateException If the date format is incorrect.
     */
    public Deadline(String description, String by) throws InvalidDateException {
        super(description);
        this.by = parseDateTime(by);
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
     * Returns the deadline of the task.
     *
     * @return The deadline as a {@code LocalDateTime} object.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
