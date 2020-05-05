package testLab4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTask {
    private final Date startDate = new Date("01/14/2020");
    private final Date endDate = new Date("02/14/2020");

    @DisplayName("TC1_ECP - Task valid - data si interval valide")
    @ParameterizedTest
    @ValueSource(strings = {"Task", "Task1234@!@#!$@#$!#!@#!@#!@#!@#aaaaaaaaa"})
    public void correctTask(String title) {
        Task task = new Task(title, startDate, endDate, 1);
        assertEquals(task.getTitle(), title);
        assertEquals(task.getStartTime(), startDate);
        assertEquals(task.getEndTime(), endDate);
        assertEquals(task.getRepeatInterval(), 1);
    }

    @DisplayName("TC2_ECP - Task invalid - data de inceput este negativa")
    @ParameterizedTest
    @ValueSource(ints = {-10, -1000})
    public void wrongTitle(long timeValue) {
        Date wrongDate = new Date();
        wrongDate.setTime(timeValue);
        wrongDate.setTime(timeValue);
        assertThrows(IllegalArgumentException.class, () -> new Task("Task", wrongDate, endDate, 1));
    }

}