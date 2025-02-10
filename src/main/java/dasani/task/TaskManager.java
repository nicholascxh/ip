package dasani.task;

import dasani.Dasani;
import dasani.exception.DasaniException;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private final Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public void addTask(Task task) {
        if (taskCount >= MAX_TASKS) {
            System.out.println(" 🔵 [Dasani]: Task list is full. ❌");
            return;
        }
        tasks[taskCount++] = task;
        Dasani.printLine();
        System.out.println(" 🔵 [Dasani]: Added: \"" + task + "\" 💬");
        Dasani.printLine();
    }

    public void displayTasks() {
        Dasani.printLine();
        if (taskCount == 0) {
            System.out.println(" 🔵 [Dasani]: Your task list is empty. Add some task! ❌");
        } else {
            System.out.println(" 🔵 [Dasani]: The list is:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        Dasani.printLine();
    }

    public void markTask(String command, boolean markAsDone) throws DasaniException {
        try {
            if (command.isEmpty()) {
                throw new DasaniException(" 🔵 [Dasani]: Please provide a task number after 'mark' ❌");
            }

            int taskNumber = Integer.parseInt(command);

            if (taskNumber <= 0 || taskNumber > MAX_TASKS) {
                throw new DasaniException(" 🔵 [Dasani]: Task number needs to be between 1 and 100 ❌");
            }

            if (tasks[taskNumber - 1] == null) {
                throw new DasaniException(" 🔵 [Dasani]: Task number " + taskNumber + " does not exist ❌");
            }

            if (markAsDone) {
                if (tasks[taskNumber - 1].isDone()) {
                    displayTaskStatus(taskNumber, "already done", "✅");
                } else {
                    tasks[taskNumber - 1].markAsDone();
                    displayTaskStatus(taskNumber, "marked as done", "✅");
                }
            } else {
                if (!tasks[taskNumber - 1].isDone()) {
                    displayTaskStatus(taskNumber, "already not done", "🔄");
                } else {
                    tasks[taskNumber - 1].markAsNotDone();
                    displayTaskStatus(taskNumber, "marked as not done", "🔄");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(" 🔵 [Dasani]: Please enter a valid task number ❌");
        }
    }

    private void displayTaskStatus(int taskNumber, String status, String emoji) {
        Dasani.printLine();
        System.out.println(" 🔵 [Dasani]: Task: '" + taskNumber + ". " + tasks[taskNumber - 1] + "' is " + status + ". " + emoji);
        Dasani.printLine();
    }
}
