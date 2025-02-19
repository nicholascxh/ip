package dasani.task;

import dasani.Dasani;
import dasani.exception.DasaniException;
import dasani.task.type.Deadline;
import dasani.task.type.Event;
import dasani.task.type.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import java.util.ArrayList;

public class TaskManager {

    private final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/Dasani.txt";

    public TaskManager() {
        loadTasks();
    }
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
        Dasani.printLine();
        System.out.println("[Dasani]: Added: \"" + task + "\"");
        Dasani.printLine();
    }

    public void deleteTask(String command) throws DasaniException {
        try {
            if (command.isEmpty()) {
                throw new DasaniException("[Dasani]: Please provide a task number after 'delete'");
            }

            int taskNumber = Integer.parseInt(command);

            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new DasaniException("[Dasani]: Task number needs to be between 1 and " + tasks.size());
            }

            Task removedTask = tasks.remove(taskNumber - 1);
            saveTasks();
            Dasani.printLine();
            System.out.println("[Dasani]: Deleted: \"" + removedTask + "\"");
            Dasani.printLine();

        } catch (NumberFormatException e) {
            System.out.println("[Dasani]: Please enter a valid task number");
        }


    }

    public void displayTasks() {
        Dasani.printLine();
        if (tasks.isEmpty()) {
            System.out.println("[Dasani]: Your task list is empty. Add some task!");
        } else {
            System.out.println("[Dasani]: The list is:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        Dasani.printLine();
    }

    public void markTask(String command, boolean markAsDone) throws DasaniException {
        try {
            if (command.isEmpty()) {
                throw new DasaniException("[Dasani]: Please provide a task number after 'mark'");
            }

            int taskNumber = Integer.parseInt(command);

            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new DasaniException("[Dasani]: Task number needs to be between 1 and " + tasks.size());
            }

            if (markAsDone) {
                if (tasks.get(taskNumber - 1).isDone()) {
                    displayTaskStatus(taskNumber, "already done");
                } else {
                    tasks.get(taskNumber - 1).markAsDone();
                    saveTasks();
                    displayTaskStatus(taskNumber, "marked as done");
                }
            } else {
                if (!tasks.get(taskNumber - 1).isDone()) {
                    displayTaskStatus(taskNumber, "already not done");
                } else {
                    tasks.get(taskNumber - 1).markAsNotDone();
                    saveTasks();
                    displayTaskStatus(taskNumber, "marked as not done");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("[Dasani]: Please enter a valid task number");
        }
    }

    private void displayTaskStatus(int taskNumber, String status) {
        Dasani.printLine();
        System.out.println("[Dasani]: Task: '" + taskNumber + ". " + tasks.get(taskNumber - 1) + "' is " + status + ".");
        Dasani.printLine();
    }

    public void saveTasks() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Create directory if it doesn't exist

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : tasks) {
                writer.write(taskToFileFormat(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("[Dasani]: Error saving tasks");
        }
    }

    private String taskToFileFormat(Task task) {
        String type = task instanceof dasani.task.type.Todo ? "T" :
                task instanceof dasani.task.type.Deadline ? "D" :
                        task instanceof dasani.task.type.Event ? "E" : "U"; // U = Unknown

        String status = task.isDone() ? "1" : "0";
        String description = task.getDescription();

        if (task instanceof dasani.task.type.Deadline) {
            return type + " | " + status + " | " + description + " | " + ((Deadline) task).getBy();
        } else if (task instanceof dasani.task.type.Event) {
            return type + " | " + status + " | " + description + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        } else {
            return type + " | " + status + " | " + description;
        }
    }

    public void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("[Dasani]: No previous tasks found. Starting fresh!");
            return; // No file exists, so no tasks to load
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                parseTaskFromFile(scanner.nextLine());
            }
            scanner.close();
            System.out.println("[Dasani]: Successfully loaded tasks from storage.");
        } catch (IOException e) {
            System.out.println("[Dasani]: Error loading tasks");
        }
    }

    private void parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return; // Ignore malformed lines
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        if (type.equals("T")) {
            task = new Todo(description);
        } else if (type.equals("D") && parts.length == 4) {
            task = new Deadline(description, parts[3]);
        } else if (type.equals("E") && parts.length == 5) {
            task = new Event(description, parts[3], parts[4]);
        }

        if (task != null) {
            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
    }
}
