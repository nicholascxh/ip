package dasani.task.type;

import dasani.task.Task;

/**
 * Represents a Todo task.
 * A Todo task contains a description but does not have a specific date or time.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
