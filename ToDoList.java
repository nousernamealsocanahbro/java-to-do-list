// add input validation later...

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> tasks = new ArrayList<String>();
    private static File file;

    private static void createFile(String fileName) {
        file = new File(fileName + ".txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                // adds existing tasks to tasks array, if any
                Scanner fileWriter = new Scanner(file);
                while (fileWriter.hasNext()) {
                    tasks.add(fileWriter.nextLine());
                }
                fileWriter.close();

                System.out.println("File already exists: " + file.getAbsolutePath()+ "No file created.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
    }

    private static void writeTasksToFile() {
        try (FileWriter fileWriter = new FileWriter(file);) {
            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getTaskNumber() {
        printTasks();

        System.out.println("Enter task you want to modify");
        int taskNumber = scanner.nextInt() - 1;
        scanner.nextLine();

        return taskNumber;
    }

    private static void addTask() {
        System.out.println("Enter task you want to add");
        tasks.add(scanner.nextLine());

        writeTasksToFile();
        System.out.println("Task added.\n");
    }

    private static void modifyTask() {
        int taskNumber = getTaskNumber();

        System.out.println("Enter new task");

        tasks.set(taskNumber, scanner.nextLine());

        writeTasksToFile();

        System.out.println("Task modified.\n");
    }

    private static void removeTask() {
        int taskNumber = getTaskNumber();

        tasks.remove(taskNumber);

        writeTasksToFile();

        System.out.println("Task removed.\n");
    }

    public static void main(String[] args) {
        System.out.println("Input file name without extensions");
        createFile(scanner.nextLine());

        while (true) {
            System.out.println("1. View tasks");
            System.out.println("2. Add task");
            System.out.println("3. Modify task");
            System.out.println("4. Remove task");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear leftover newline

            switch (choice) {
                case 1:
                    printTasks();
                    System.out.println("Press any key to continue..."); // give user time to read
                    scanner.nextLine();
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
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}