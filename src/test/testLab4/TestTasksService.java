package testLab4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTasksService {

    private TasksService tasksService;
    private ArrayTaskList arrayTaskList;
    private Task task1, task2, task3;

    @BeforeAll
    public void setUp() {
        arrayTaskList = Mockito.mock(ArrayTaskList.class);
        task1 = new Task("task1", new Date("01/14/2020"), new Date("02/14/2020"), 2);
        task2 = new Task("task1", new Date("01/14/2020"), new Date("05/14/2020"), 2);
        task3 = new Task("task1", new Date("02/14/2020"), new Date("03/14/2020"), 2);
        tasksService = new TasksService(arrayTaskList);
    }

    @DisplayName("Test TasksService getObservableList method")
    @Test
    public void testTasksServiceGetObservableList() {
        Integer expectedSize = 3;
        Mockito.when(arrayTaskList.getAll()).thenReturn(Arrays.asList(task1, task2, task3));
        Integer actualObservableListSize = tasksService.getObservableList().size();
        assertEquals(expectedSize, actualObservableListSize);

    }

    @DisplayName("Test TasksService filterTasks method")
    @Test
    public void testArrayTaskListFilterTasks() {
        //filterTasks
        Mockito.when(arrayTaskList.getAll()).thenReturn(Arrays.asList(task1, task2, task3, task1, task2, task3));
        Integer expectedSizeOfFilteredTasks = 0;
        Integer actualListSize = StreamSupport
                .stream(tasksService.filterTasks(new Date("01/14/2020"),new Date("03/14/2020")).spliterator(), false)
                .collect(Collectors.toList()).size();
        assertEquals(expectedSizeOfFilteredTasks, actualListSize);
    }

}
