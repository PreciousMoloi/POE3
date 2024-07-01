/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe3;
import javax.swing.JOptionPane;
/**
 *
 * @author Tumi
 */
public class Task {
    String[] taskNames;
    String[] taskDescriptions;
    float[] taskDurations;
    String[] developerNames;
    String[] taskStatuses;
    String[] taskIDs;
    int numTasks = 0;
int totalHours = 0;

    public void runTaskManagement(String name, String surname) {
        int choice;
        do {
            choice = showMenu();

            if (choice == 1) {
                optionAddTasks(name, surname);
            } else if (choice == 2) {
                optionShowReport();
            } else {
                JOptionPane.showMessageDialog(null, "Exiting System...");
            }
        } while (choice != 3);
    }

    public int showMenu() {
        int choice = 0;
        try {
            String menuMessage = "\tMenu:\n\n"
                    + "============================================================\n"
                    + "\t1. Option 1 - Add Tasks\n"
                    + "\t2. Option 2 - Show Report\n"
                    + "\t3. Option 3 - Exit\n"
                    + "============================================================\n"
                    + "Enter your choice:";
            String choiceString = JOptionPane.showInputDialog(null, menuMessage);
            choice = Integer.parseInt(choiceString);
            if (choice < 1 || choice > 3) {
                JOptionPane.showMessageDialog(null, "Invalid option. Enter a number from 1 to 3.");
                choice = 0;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid option. Enter a number from 1 to 3.");
            choice = 0;
        }
        return choice;
    }

    public void optionAddTasks(String name, String surname) {
        int tasks = Integer.parseInt(JOptionPane.showInputDialog(null, "How many tasks do you want to add?"));

        taskNames = new String[tasks];
        taskDescriptions = new String[tasks];
        taskDurations = new float[tasks];
        developerNames = new String[tasks];
        taskStatuses = new String[tasks];
        taskIDs = new String[tasks];

        for (int Counter = 0; Counter < tasks; Counter++) {
            JOptionPane.showMessageDialog(null, "Option 1 - Add Tasks");

            String taskName = JOptionPane.showInputDialog(null, "Enter the task name:");
            taskNames[Counter] = taskName;

            while (true) {
                String taskDescription = JOptionPane.showInputDialog(null,
                        "Enter the task's description (50 or fewer characters):");
                if (checkTaskDescription(taskDescription)) {
                    taskDescriptions[Counter] = taskDescription;
                    JOptionPane.showMessageDialog(null, "Task description successfully captured.");
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of 50 or fewer characters.");
                }
            }

            String developerFirstName = JOptionPane.showInputDialog(null, "Enter the developer's first name:");
            String developerLastName = JOptionPane.showInputDialog(null, "Enter the developer's last name:");
            developerNames[Counter] = developerFirstName + " " + developerLastName;

            float taskDuration = returnTotalHours();
            taskDurations[Counter] = taskDuration;
            totalHours += taskDuration;

            String taskStatus = TaskStatus();
            taskStatuses[Counter] = taskStatus;

            String taskID = createTaskID(taskName, Counter, surname);
            taskIDs[Counter] = taskID;

            numTasks++;
        }
    }

    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    public float returnTotalHours() {
        float totalHours = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                String hoursString = JOptionPane.showInputDialog(null, "Enter the task duration in hours:");
                totalHours = Float.parseFloat(hoursString);
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            }
        }
        return totalHours;
    }

    public String TaskStatus() {
        String[] options = {"To Do", "In Progress", "Done"};
        int statusIndex = JOptionPane.showOptionDialog(null, "Select the task status:", "Task Status",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String taskStatus = options[statusIndex];
        return taskStatus;
    }

    public String createTaskID(String taskName, int index, String surname) {
        String initials = taskName.substring(0, 2).toUpperCase();
        String id = initials + index + surname.substring(0, 2).toUpperCase();
        return id;
    }

    public void optionShowReport() {
        JOptionPane.showMessageDialog(null, "Option 2 - Report");

        if (numTasks > 0) {
            String reportString = JOptionPane.showInputDialog(null,
                    "Select an option:\n\n"
                    + "1. Display the Developer, Task Name, Task Duration, and Task ID for all tasks with the status 'Done'\n"
                    + "2. Display the Developer and Duration of the task with the longest duration\n"
                    + "3. Search for a task by Task Name and display the Task Name, Developer, Task Status, and Task ID\n"
                    + "4. Search for all tasks assigned to a developer and display the Task Name, Task Status, and Task ID\n"
                    + "5. Delete a task using the Task Name\n"
                    + "6. Display a report that lists the full details of all captured tasks");

            int reportMenu = Integer.parseInt(reportString);

            if (reportMenu == 1) {
                TasksWithStatus("Done");
            } else if (reportMenu == 2) {
                TaskLongestDuration();
            } else if (reportMenu == 3) {
                searchTaskName();
            } else if (reportMenu == 4) {
                searchTaskDeveloper();
            } else if (reportMenu == 5) {
                deleteTaskName();
            } else if (reportMenu == 6) {
                displayAllTasks();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tasks added yet.");
        }
    }

    public void TasksWithStatus(String status) {
        JOptionPane.showMessageDialog(null, "Tasks with status '" + status + "':");
        boolean foundTasks = false;
        for (int Counter = 0; Counter < numTasks; Counter++) {
            if (taskStatuses[Counter].equalsIgnoreCase(status)) {
                foundTasks = true;
                outputTasks(Counter);
            }
        }
        if (!foundTasks) {
            JOptionPane.showMessageDialog(null, "No tasks with status '" + status + "' found.");
        }
    }
    public void TaskLongestDuration() {
        float longestDuration = -1;
        int longestDurationIndex = -1;
        for (int Counter = 0; Counter < numTasks; Counter++) {
            if (taskDurations[Counter] > longestDuration) {
                longestDuration = taskDurations[Counter];
                longestDurationIndex = Counter;
            }
        }
        if (longestDurationIndex != -1) {
            JOptionPane.showMessageDialog(null, "Task with longest duration:\n"
                    + "Developer: " + developerNames[longestDurationIndex] + "\n"
                    + "Duration: " + taskDurations[longestDurationIndex] + " hours\n"
                    + "Task ID: " + taskIDs[longestDurationIndex]);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found.");
        }
    }

    public void searchTaskName() {
        String taskName = JOptionPane.showInputDialog(null, "Enter the task name:");
        boolean foundTask = false;
        for (int Counter = 0; Counter < numTasks; Counter++) {
            if (taskNames[Counter].equalsIgnoreCase(taskName)) {
                foundTask = true;
                outputTasks(Counter);
            }
        }
        if (!foundTask) {
            JOptionPane.showMessageDialog(null, "Task '" + taskName + "' not found.");
        }
    }

    public void searchTaskDeveloper() {
        String developer = JOptionPane.showInputDialog(null, "Enter the developer's name:");
        boolean foundTasks = false;
        for (int Counter = 0; Counter < numTasks; Counter++) {
            if (developerNames[Counter].equalsIgnoreCase(developer)) {
                foundTasks = true;
                outputTasks(Counter);
            }
        }
        if (!foundTasks) {
            JOptionPane.showMessageDialog(null, "No tasks assigned to developer '" + developer + "' found.");
        }
    }

    public void deleteTaskName() {
        String taskName = JOptionPane.showInputDialog(null, "Enter the task name:");
        boolean deleted = false;
        for (int Counter = 0; Counter < numTasks; Counter++) {
            if (taskNames[Counter].equalsIgnoreCase(taskName)) {
                deleted = true;
                shiftTasksLeft(Counter);
                numTasks--;
                JOptionPane.showMessageDialog(null, "Task '" + taskName + "' deleted.");
                break;
            }
        }
        if (!deleted) {
            JOptionPane.showMessageDialog(null, "Task '" + taskName + "' not found.");
        }
    }

    public void shiftTasksLeft(int index) {
        for (int Counter = index; Counter < numTasks - 1; Counter++) {
            taskNames[Counter] = taskNames[Counter + 1];
            taskDescriptions[Counter] = taskDescriptions[Counter + 1];
            taskDurations[Counter] = taskDurations[Counter + 1];
            developerNames[Counter] = developerNames[Counter + 1];
            taskStatuses[Counter] = taskStatuses[Counter + 1];
            taskIDs[Counter] = taskIDs[Counter + 1];
        }
    }

    public void displayAllTasks() {
        for (int Counter = 0; Counter < numTasks; Counter++) {
            JOptionPane.showMessageDialog(null, "Task " + (Counter + 1) + " Details:\n");
            outputTasks(Counter);
        }
    }

    public void outputTasks(int index) {
        JOptionPane.showMessageDialog(null,
                "Task Name: " + taskNames[index] + "\n"
                + "Task Description: " + taskDescriptions[index] + "\n"
                + "Task Duration: " + taskDurations[index] + " hours\n"
                + "Developer: " + developerNames[index] + "\n"
                + "Task Status: " + taskStatuses[index] + "\n"
                + "Task ID: " + taskIDs[index]);
    } 
}
