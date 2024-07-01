/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package poe3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

/**
 *
 * @author Tumi
 */
public class TaskTest {
    
     private Task taskManager;

  
     @BeforeEach
    public void setUp() {
        taskManager = new Task();
        addTestTask("Mike Smith", "Create Login", 5, "To Do");
        addTestTask("Edward Harrison", "Create Add Features", 8, "Doing");
        addTestTask("Samantha Paulson", "Create Reports", 2, "Done");
        addTestTask("Glenda Oberholzer", "Add Arrays", 11, "To Do");
    }

    
    private void addTestTask(String developer, String taskName, float duration, String status) {
        taskManager.numTasks++;
        int index = taskManager.numTasks - 1;
        if (taskManager.taskNames == null) {
            taskManager.taskNames = new String[10];
            taskManager.taskDescriptions = new String[10];
            taskManager.taskDurations = new float[10];
            taskManager.developerNames = new String[10];
            taskManager.taskStatuses = new String[10];
            taskManager.taskIDs = new String[10];
        }
        taskManager.taskNames[index] = taskName;
        taskManager.taskDescriptions[index] = taskName + " description";
        taskManager.taskDurations[index] = duration;
        taskManager.developerNames[index] = developer;
        taskManager.taskStatuses[index] = status;
        taskManager.taskIDs[index] = taskManager.createTaskID(taskName, index, developer.split(" ")[1]);
    }

    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        assertArrayEquals(expectedDevelopers, taskManager.developerNames);
    }

    @Test
    public void testDisplayDeveloperAndDurationForLongestTask() {
        float longestDuration = -1;
        String longestDeveloper = "";
        for (int i = 0; i < taskManager.numTasks; i++) {
            if (taskManager.taskDurations[i] > longestDuration) {
                longestDuration = taskManager.taskDurations[i];
                longestDeveloper = taskManager.developerNames[i];
            }
        }
        assertEquals("Glenda Oberholzer", longestDeveloper);
        assertEquals(11, longestDuration);
    }

    @Test
    public void testSearchTaskByName() {
        String taskName = "Create Login";
        boolean found = false;
        for (int i = 0; i < taskManager.numTasks; i++) {
            if (taskManager.taskNames[i].equals(taskName)) {
                assertEquals("Mike Smith", taskManager.developerNames[i]);
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testSearchTasksByDeveloper() {
        String developer = "Samantha Paulson";
        boolean found = false;
        for (int i = 0; i < taskManager.numTasks; i++) {
            if (taskManager.developerNames[i].equals(developer)) {
                assertEquals("Create Reports", taskManager.taskNames[i]);
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testDeleteTaskByName() {
        String taskName = "Create Reports";
        boolean deleted = false;
        for (int i = 0; i < taskManager.numTasks; i++) {
            if (taskManager.taskNames[i].equals(taskName)) {
                taskManager.shiftTasksLeft(i);
                taskManager.numTasks--;
                deleted = true;
                break;
            }
        }
        assertTrue(deleted);
        for (int i = 0; i < taskManager.numTasks; i++) {
            assertNotEquals(taskName, taskManager.taskNames[i]);
        }
    }

}