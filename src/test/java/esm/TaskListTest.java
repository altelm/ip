package esm;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class TaskListTest {

    @Test
    public void mark_marksCorrectTask() {
        TaskList taskList= new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.markTask(1);
        assertEquals("X", taskList.getTask(1).getDone());
    }

    @Test
    public void unmark_unmarksCorrectTask() {
        TaskList taskList= new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.markTask(1);
        taskList.unmarkTask(1);
        assertEquals("", taskList.getTask(1).getDone());
    }

    @Test
    public void remove_emptyList_doesNotremove() {
        TaskList taskList= new TaskList(new ArrayList<Task>());
        Task temp = taskList.removeTask(2);
        assertEquals(null, temp);
    }

    @Test
    public void getSize_returnSizecorrect() {
        TaskList taskList= new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.addTask(new ToDo("write"));
        assertEquals(2, taskList.getSize());
    }

    @Test
    public void remove_populatedList_removesCorrectly() {
        TaskList taskList= new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.addTask(new ToDo("write"));
        taskList.removeTask(1);
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void remove_invalidIndex_removesCorrectly() {
        TaskList taskList= new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        taskList.addTask(new ToDo("write"));
        taskList.removeTask(-1);
        assertEquals(2, taskList.getSize());
    }

    @Test
    public void add_correctlyUpdates() {
        TaskList taskList= new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("read"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void getTask_correctIndex_givesCorrectTask() {
        TaskList taskList= new TaskList(new ArrayList<Task>());
        Task task = new ToDo("read");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(1));
    }

    @Test
    public void getTask_incorrectIndex_givesCorrectTask() {
        TaskList taskList= new TaskList(new ArrayList<Task>());
        assertEquals(null, taskList.getTask(3));
    }

    @Test
    public void getList_givesCorrectList() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList= new TaskList(list);
        assertEquals(list, taskList.getList());
    }

    @Test
    public void print_twoTasks_printCorrectly() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.addTask(new ToDo("write"));
        taskList.addTask(new ToDo("cook"));

        taskList.printList();
        System.setOut(original);
        String output = out.toString();
        assertTrue(output.contains("1. "));
        assertTrue(output.contains("2. "));
        assertTrue(output.contains("write"));
        assertTrue(output.contains("cook"));

    }

    @Test
    public void print_emptyList_printCorrectly() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        TaskList taskList = new TaskList(new ArrayList<Task>());
        taskList.printList();
        System.setOut(original);
        String output = out.toString();
        assertTrue(output.contains("Thy ledger is empty"));

    }
}
