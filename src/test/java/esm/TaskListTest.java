package esm;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Unit tests for the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests that the markTask method correctly marks the task at the specified index as done.
     */
    @Test
    public void mark_marksCorrectTask() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.markTask(1);
        assertEquals("X", taskList.getTask(1).getDone());
    }

    /**
     * Tests that the unmarkTask method correctly unmarks the task at the specified index as not done.
     */
    @Test
    public void unmark_unmarksCorrectTask() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.markTask(1);
        taskList.unmarkTask(1);
        assertEquals("", taskList.getTask(1).getDone());
    }

    /**
     * Tests that removeTask on an empty list returns null and does not remove anything.
     */
    @Test
    public void remove_emptyList_doesNotremove() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task temp = taskList.removeTask(2);
        assertEquals(null, temp);
    }

    /**
     * Tests that getSize returns the correct size of the task list.
     */
    @Test
    public void getSize_returnSizecorrect() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.addTask(new ToDo("write"));
        assertEquals(2, taskList.getSize());
    }

    /**
     * Tests that removeTask correctly removes a task from a populated list.
     */
    @Test
    public void remove_populatedList_removesCorrectly() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.addTask(new ToDo("write"));
        taskList.removeTask(1);
        assertEquals(1, taskList.getSize());
    }

    /**
     * Tests that removeTask with an invalid index does not remove any task.
     */
    @Test
    public void remove_invalidIndex_removesCorrectly() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.addTask(new ToDo("write"));
        taskList.removeTask(-1);
        assertEquals(2, taskList.getSize());
    }

    /**
     * Tests that addTask correctly adds a task.
     */
    @Test
    public void add_correctlyUpdates() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        assertEquals(1, taskList.getSize());
    }

    /**
     * Tests that getTask returns the correct task at a valid index.
     */
    @Test
    public void getTask_correctIndex_givesCorrectTask() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = new ToDo("read");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(1));
    }

    /**
     * Tests that getTask returns null when accessing an invalid index.
     */
    @Test
    public void getTask_incorrectIndex_givesCorrectTask() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        assertEquals(null, taskList.getTask(3));
    }

    /**
     * Tests that getList returns the underlying task list.
     */
    @Test
    public void getList_givesCorrectList() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList(list);
        assertEquals(list, taskList.getList());
    }

    /**
     * Tests that printList correctly returns the expected output the tasks in the list.
     */
    @Test
    public void print_twoTasks_printCorrectly() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("write"));
        taskList.addTask(new ToDo("cook"));

        String output = taskList.printList();
        assertTrue(output.contains("1."));
        assertTrue(output.contains("2."));
        assertTrue(output.contains("write"));
        assertTrue(output.contains("cook"));

    }

    /**
     * Tests that printList correctly outputs a message when the task list is empty.
     */
    @Test
    public void print_emptyList_printCorrectly() {
        TaskList taskList = new TaskList(new ArrayList<>());
        String output = taskList.printList();
        assertTrue(output.contains("Thy ledger is empty"));

    }

    /**
     * Tests that findTask correctly finds a task that is present in a non-empty list.
     */
    @Test
    public void findTask_findExistingTask_returnsCorrectList() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.addTask(new ToDo("write"));
        TaskList found = taskList.findTask("read");
        assertEquals(1, found.getSize());
        assertEquals("read", found.getTask(1).getName());
    }

    /**
     * Tests that findTask returns an empty list when the task is not found in a non-empty list.
     */
    @Test
    public void findTask_findNonExistingTask_returnsEmptyList() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.addTask(new ToDo("write"));
        TaskList found = taskList.findTask("cook");
        assertEquals(0, found.getSize());
    }

    /**
     * Tests that findTask returns an empty list when the provided list to search through is empty.
     */
    @Test
    public void findTask_emptyList_returnsEmptyList() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        TaskList found = taskList.findTask("read");
        assertEquals(0, found.getSize());
    }

    /**
     * Tests that sortList correctly sorts the list by nearing deadline.
     */
    @Test
    public void sortList_sortByDeadline_sortsCorrectly() throws DateException {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new Deadline("read", "2024-12-01"));
        taskList.addTask(new Deadline("cook", "2024-12-01"));
        taskList.addTask(new Event("write", "2024-11-01", "2024-11-30"));
        TaskList sorted = taskList.sortList("d");
        assertEquals("write", sorted.getTask(1).getName());
        assertEquals("read", sorted.getTask(2).getName());
        assertEquals("cook", sorted.getTask(3).getName());
    }

    /**
     * Tests that sortList correctly sorts the list alphabetically by name.
     */
    @Test
    public void sortList_sortByName_sortsCorrectly() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("write"));
        taskList.addTask(new ToDo("read"));
        TaskList sorted = taskList.sortList("a");
        assertEquals("read", sorted.getTask(1).getName());
        assertEquals("write", sorted.getTask(2).getName());
    }

    /**
     * Tests that sortList with an empty list returns an empty list.
     */
    @Test
    public void sortList_emptyList_returnsEmptyList() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        TaskList sorted = taskList.sortList("a");
        assertTrue(sorted.getSize() == 0);
    }

}
