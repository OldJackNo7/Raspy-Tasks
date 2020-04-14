package testLab2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tasks.model.ArrayTaskList;
import tasks.services.TasksService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBVA {
    private TasksService tasksService;

    @BeforeAll
    public void setUp() {
        ArrayTaskList taskList = new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Test
    public void emptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> tasksService.createTask("", new Date(), false, new Date(), 1, false));
    }

    @Test
    public void lowerLimitTitle() {
        tasksService.createTask("a", new Date(), false, new Date(), 1, false);
    }

    @Test
    public void upperLimitTitle() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 20; i++) {
            stringBuilder.append("a");
        }
        String title = stringBuilder.toString();
        tasksService.createTask(title, new Date(), false, new Date(), 1, false);
    }

    @Test
    public void aboveUpperLimitTitle() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 21; i++) {
            stringBuilder.append("a");
        }
        String title = stringBuilder.toString();
        assertThrows(IllegalArgumentException.class, () -> tasksService.createTask(title, new Date(), false, new Date(), 1, false));
    }

    @Test
    public void underLowerLimitInterval() {
        assertThrows(IllegalArgumentException.class, () -> tasksService.createTask("", new Date(), false, new Date(), 0, false));
    }

    @Test
    public void lowerLimitInterval() {
        tasksService.createTask("a", new Date(), false, new Date(), 1, false);
    }

    @Test
    public void upperLimitInterval() {
        tasksService.createTask("a", new Date(), false, new Date(), 60, false);
    }

    @Test
    public void aboveUpperLimitInterval() {
        tasksService.createTask("a", new Date(), false, new Date(), 61, false);
    }
}
