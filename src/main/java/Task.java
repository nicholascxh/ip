public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false; // default status: not done
    }

    // Mark task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Mark task as not done
    public void markAsNotDone() {
        this.isDone = false;
    }

    // Get status icon for display
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    // Display task with status
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}