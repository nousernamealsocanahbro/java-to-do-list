import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> tasks = new ArrayList<String>();
    private static Path file;

    private static int validateChoice(Integer min, Integer max) {
        while (true) {
            if (sc.hasNextInt()) {
                int number = sc.nextInt();
                sc.nextLine();

                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Number must be " + min + " and ");
                }
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }

    }

    private static void saveTasks() {
        try {
            Files.write(file, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadTasks() {
        try {
            tasks.addAll(Files.readAllLines(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile(String fileName) {
        file = Paths.get(fileName);

        if (Files.notExists(file)) {
            try {
                Files.createFile(file);
                System.out.println("File created:" + file.toAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loadTasks();
            System.out.println("File already exists. Tasks loaded into memory:" + file.toAbsolutePath());
        }
    }

    private static int getTaskNumber() {
        printTasks();

        System.out.println("Enter task number");
        int taskNumber = validateChoice(1, tasks.size()) - 1;

        return taskNumber;
    }

    private static void printTasks() {
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        } else {
            System.out.println("No tasks to view.");
        }
    }

    private static void addTask() {
        System.out.println("Enter task you want to add");

        tasks.add(sc.nextLine());
        System.out.println("Task added.");
    }

    private static void modifyTask() {
        printTasks();
        System.out.println("Enter task number you want to modify");
        int taskNumber = getTaskNumber();

        System.out.println("Enter new task");

        tasks.set(taskNumber, sc.nextLine());
        System.out.println("Task modified.");
    }

    private static void removeTask() {
        printTasks();
        System.out.println("Enter task number you want to remove");
        int taskNumber = getTaskNumber();

        tasks.remove(taskNumber);
        System.out.println("Task removed.");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Input file name");
        createFile(sc.nextLine() + ".txt");

        while (true) {
            System.out.println("1. View tasks");
            System.out.println("2. Add task");
            System.out.println("3. Modify task");
            System.out.println("4. Remove task");
            System.out.println("5. Quit program");
            
            System.out.println("Enter choice");
            int choice = validateChoice(1, 5);

            switch (choice) {
                case 1:
                    printTasks();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    modifyTask();
                    break;
                case 4:
                    removeTask();
                    break;
                case 5:
                    saveTasks();
                    System.exit(0);
            }
        }
    }
}