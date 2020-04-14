package testLab2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tasks.model.ArrayTaskList;
import tasks.services.TasksService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBVA {
    private TasksService tasksService;
    private final Date startDate = new Date("01/14/2020");
    private final Date endDate = new Date("02/14/2020");

    @BeforeAll
    public void setUp() {
        ArrayTaskList taskList = new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Test
    @DisplayName("TC1_BVA - Task invalid - titlu invalid")
    public void emptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> tasksService.createTask("", startDate, false, endDate, 1, false));
    }

    @Test
    @DisplayName("TC2_BVA - Task valid - lungimea titlului contine limita inferioara valida")
    public void lowerLimitTitle() {
        tasksService.createTask("a", startDate, false, endDate, 1, false);
    }

    @Test
    @DisplayName("TC3_BVA - Task valid - lungimea titlului contine limita superioara valida")
    public void upperLimitTitle() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 20; i++) {
            stringBuilder.append("a");
        }
        String title = stringBuilder.toString();
        tasksService.createTask(title, startDate, false, endDate, 1, false);
    }

    @Test
    @DisplayName("TC4_BVA - Task invalid - lungimea titlului contine limita superioara invalida")
    public void aboveUpperLimitTitle() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 21; i++) {
            stringBuilder.append("a");
        }
        String title = stringBuilder.toString();
        assertThrows(IllegalArgumentException.class, () -> tasksService.createTask(title, startDate, false, endDate, 1, false));
    }

    @Test
    @DisplayName("TC5_BVA - Task invalid - lungimea titlului contine limita inferioara invalida")
    public void underLowerLimitInterval() {
        assertThrows(IllegalArgumentException.class, () -> tasksService.createTask("", startDate, false, endDate, 0, false));
    }

    @Test
    @DisplayName("TC6_BVA - Task invalid - intervalul este la limita inferioara [1,60]")
    public void lowerLimitInterval() {
        tasksService.createTask("a", startDate, false, endDate, 1, false);
    }

    @Test
    @DisplayName("TC7_BVA - Task invalid - intervalul este la limita superioara [1,60]")
    public void upperLimitInterval() {
        tasksService.createTask("a", startDate, false, endDate, 60, false);
    }

    @Test
    @DisplayName("TC8_BVA - Task invalid - intervalul este in afara limitei superioara [1,60]")
    public void aboveUpperLimitInterval() {
        tasksService.createTask("a", startDate, false, endDate, 61, false);
    }
}
