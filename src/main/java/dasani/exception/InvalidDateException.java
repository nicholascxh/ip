package dasani.exception;

/**
 * Exception thrown when an invalid date format is provided for Deadline or Event tasks.
 */
public class InvalidDateException extends DasaniException {
    /**
     * Constructs an InvalidDateException with a default error message.
     */
    public InvalidDateException() {
        super("Invalid date format! Use: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
    }

    /**
     * Constructs an InvalidDateException with a custom error message.
     * @param message Custom error message.
     */
    public InvalidDateException(String message) {
        super(message);
    }
}