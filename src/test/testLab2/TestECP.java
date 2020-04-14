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
    private final Date startDate = new Date("01/14/2020");
    private final Date endDate = new Date("02/14/2020");

    @BeforeAll
    public void setUp() {
        ArrayTaskList taskList = new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Order(1)
    @DisplayName("Task valid - titlu si interval valid")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    public void correctTask2(int interval) {
        String title = "Task";
        Task task1 = new Task(title, startDate, endDate, interval);
        Task task2 = tasksService.createTask(title, startDate, true, endDate, interval, false);
        assertEquals(task1.getTitle(), task2.getTitle());
        assertEquals(task1.getStartTime(), task2.getStartTime());
        assertEquals(task1.getEndTime(), task2.getEndTime());
        assertEquals(task1.getRepeatInterval(), task2.getRepeatInterval());
        assertEquals(task1.isActive(), task2.isActive());
    }

    @Test
    @Order(2)
    @DisplayName("Task invalid - titlul mai lung de 20 caractere")
    public void wrongTitle() {
        String title = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        assertThrows(IllegalArgumentException.class, () -> tasksService.createTask(title, startDate, true, endDate, 1, false));
    }

    @Order(3)
    @DisplayName("Task invalid - intervalul nu se incadreaza in valorile [1,60]")
    @ParameterizedTest
    @ValueSource(ints = {-100, -10, 100, 1998})
    public void wrongInterval(int interval) {
        String title = "Task";
        assertThrows(IllegalArgumentException.class, () -> tasksService.createTask(title, startDate, true, endDate, interval, false));
    }
}
