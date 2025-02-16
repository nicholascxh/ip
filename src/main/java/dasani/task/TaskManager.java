package dasani.task;

import dasani.Dasani;
import dasani.exception.DasaniException;

import java.util.ArrayList;

public class TaskManager {

    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        Dasani.printLine();
        System.out.println(" 🔵 [Dasani]: Added: \"" + task + "\" 💬");
        Dasani.printLine();
    }

    public void deleteTask(String command) throws DasaniException {
        try {
            if (command.isEmpty()) {
                throw new DasaniException(" 🔵 [Dasani]: Please provide a task number after 'delete' ❌");
            }

            int taskNumber = Integer.parseInt(command);

            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new DasaniException(" 🔵 [Dasani]: Task number needs to be between 1 and " + tasks.size() + " ❌");
            }

            Task removedTask = tasks.remove(taskNumber - 1);
            Dasani.printLine();
            System.out.println(" 🔵 [Dasani]: Deleted: \"" + removedTask + "\" 💬");
            Dasani.printLine();

        } catch (NumberFormatException e) {
            System.out.println(" 🔵 [Dasani]: Please enter a valid task number ❌");
        }


    }

    public void displayTasks() {
        Dasani.printLine();
        if (tasks.isEmpty()) {
            System.out.println(" 🔵 [Dasani]: Your task list is empty. Add some task! ❌");
        } else {
            System.out.println(" 🔵 [Dasani]: The list is:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
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

            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new DasaniException(" 🔵 [Dasani]: Task number needs to be between 1 and " + tasks.size() + " ❌");
            }

            if (markAsDone) {
                if (tasks.get(taskNumber - 1).isDone()) {
                    displayTaskStatus(taskNumber, "already done", "✅");
                } else {
                    tasks.get(taskNumber - 1).markAsDone();
                    displayTaskStatus(taskNumber, "marked as done", "✅");
                }
            } else {
                if (!tasks.get(taskNumber - 1).isDone()) {
                    displayTaskStatus(taskNumber, "already not done", "🔄");
                } else {
                    tasks.get(taskNumber - 1).markAsNotDone();
                    displayTaskStatus(taskNumber, "marked as not done", "🔄");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(" 🔵 [Dasani]: Please enter a valid task number ❌");
        }
    }

    private void displayTaskStatus(int taskNumber, String status, String emoji) {
        Dasani.printLine();
        System.out.println(" 🔵 [Dasani]: Task: '" + taskNumber + ". " + tasks.get(taskNumber - 1) + "' is " + status + ". " + emoji);
        Dasani.printLine();
    }
}
