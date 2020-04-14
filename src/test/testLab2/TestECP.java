package testLab2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestECP {
    private TasksService tasksService;

    @BeforeAll
    public void setUp() {
        ArrayTaskList taskList = new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Test
    @Order(1)
    @DisplayName("Task valid - titlu si interval valid")
    public void correctTask1() {
        String title = "Task";
        Date startDate = new Date();
        Task task1 = new Task(title, startDate);
        Task task2 = tasksService.createTask(title, startDate, false, new Date(), 1, false);
        assertEquals(task1.getTitle(), task2.getTitle());
        assertEquals(task1.getStartTime(), task2.getStartTime());
        assertEquals(task1.isActive(), task2.isActive());
    }

    @Order(2)
    @DisplayName("Task valid - titlu si interval valid")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    public void correctTask2(int interval) {
        String title = "Task";
        Date startDate = new Date();
        Date endDate = new Date();
        Task task1 = new Task(title, startDate, endDate, interval);
        Task task2 = tasksService.createTask(title, startDate, true, endDate, interval, false);
        assertEquals(task1.getTitle(), task2.getTitle());
        assertEquals(task1.getStartTime(), task2.getStartTime());
        assertEquals(task1.getEndTime(), task2.getEndTime());
        assertEquals(task1.getRepeatInterval(), task2.getRepeatInterval());
        assertEquals(task1.isActive(), task2.isActive());
    }

    @Test
    @Order(3)
    @DisplayName("Task valid - titlu si interval valide")
    public void correctTask3() {
        String title = "TaskMaiBun";
        Date startDate = new Date(0);
        Date endDate = new Date();
        int interval = 5;
        boolean isActive = true;
        Task task1 = new Task(title, startDate, endDate, interval);
        task1.setActive(isActive);
        Task task2 = tasksService.createTask(title, startDate, true, endDate, interval, isActive);
        assertEquals(task1.getTitle(), title);
        assertEquals(task1.getTitle(), task2.getTitle());
        assertEquals(task1.getStartTime(), startDate);
        assertEquals(task1.getStartTime(), task2.getStartTime());
        assertEquals(task1.getEndTime(), endDate);
        assertEquals(task1.getEndTime(), task2.getEndTime());
        assertEquals(task1.getRepeatInterval(), interval);
        assertEquals(task1.getRepeatInterval(), task2.getRepeatInterval());
        assertEquals(task1.isActive(), isActive);
        assertEquals(task1.isActive(), task2.isActive());
    }

    @Test
    @Order(4)
    @DisplayName("Task invalid - titlul mai lung de 20 caractere")
    public void wrongTitle() {
        String title = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Date startDate = new Date();
        Date endDate = new Date();
        assertThrows(IllegalArgumentException.class, () -> tasksService.createTask(title, startDate, true, endDate, 1, false));
    }

    @Order(5)
    @DisplayName("Task invalid - intervalul nu se incadreaza in valorile [1,60]")
    @ParameterizedTest
    @ValueSource(ints = {-100, -10, 100, 1998})
    public void wrongInterval(int interval) {
        String title = "Task";
        Date startDate = new Date();
        Date endDate = new Date();
        assertThrows(IllegalArgumentException.class, () -> tasksService.createTask(title, startDate, true, endDate, interval, false));
    }
}
