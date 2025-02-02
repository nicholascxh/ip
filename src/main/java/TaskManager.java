public class TaskManager {
    private static int MAX_TASKS = 100;
    private Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public void addTask(String description) {
        if (taskCount >= MAX_TASKS) {
            System.out.println(" 🔵 [Dasani]: Task list is full. ❌");
            return;
        }
        tasks[taskCount++] = new Task(description);
        Dasani.printLine();
        System.out.println(" 🔵 [Dasani]: Added: \"" + description + "\" 💬");
        Dasani.printLine();
    }

    public void displayTasks() {
        Dasani.printLine();
        System.out.println(" 🔵 [Dasani]: The list is:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        Dasani.printLine();
    }

    public void markTask(String command, boolean markAsDone) {
        try {
            int taskNumber = Integer.parseInt(command.replaceAll("[^0-9]", "")) - 1;
            if (taskNumber < 0 || taskNumber >= taskCount || tasks[taskNumber] == null) {
                throw new NumberFormatException();
            }

            if (markAsDone) {
                if (tasks[taskNumber].isDone()) {
                    displayTaskStatus(taskNumber, "already done", "✅");
                } else {
                    tasks[taskNumber].markAsDone();
                    displayTaskStatus(taskNumber, "marked as done", "✅");
                }
            } else {
                if (!tasks[taskNumber].isDone()) {
                    displayTaskStatus(taskNumber, "already not done", "🔄");
                } else {
                    tasks[taskNumber].markAsNotDone();
                    displayTaskStatus(taskNumber, "marked as not done", "🔄");
                }
            }
        } catch (NumberFormatException e) {
            Dasani.printLine();
            System.out.println(" 🔵 [Dasani]: Invalid task number. ❌");
            Dasani.printLine();
        }
    }

    private void displayTaskStatus(int taskNumber, String status, String emoji) {
        Dasani.printLine();
        System.out.println(" 🔵 [Dasani]: Task: '" + (taskNumber + 1) + ". " + tasks[taskNumber] + "' is " + status + ". " + emoji);
        Dasani.printLine();
    }
}
