package dasani.task.type;

import dasani.task.Task;
import dasani.exception.InvalidDateException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Stores the deadline as a LocalDateTime object.
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a Deadline task with a description and a deadline date.
     * @param description Description of the task.
     * @param by Deadline in yyyy-MM-dd HHmm format.
     * @throws InvalidDateException If the date format is incorrect.
     */
    public Deadline(String description, String by) throws InvalidDateException {
        super(description);
        this.by = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String dateTime) throws InvalidDateException {
        try {
            return LocalDateTime.parse(dateTime.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}