package testLab4;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestArrayTaskList {

    private ArrayTaskList arrayTaskList;
    private Task task1, task2, task3;

    private final Date startDate = new Date("01/14/2020");
    private final Date endDate = new Date("02/14/2020");

    @BeforeAll
    public void setUp() {
        arrayTaskList = new ArrayTaskList();
        task1 = Mockito.mock(Task.class);
        task2 = Mockito.mock(Task.class);
        task3 = Mockito.mock(Task.class);
    }

    @DisplayName("Test ArrayTaskList.add method")
    @Test
    public void testArrayTaskListAdd() {
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        Integer expectedSize = 3;
        assertEquals(expectedSize, arrayTaskList.size());
        assertThrows(NullPointerException.class, () -> arrayTaskList.add(null));
    }

    @DisplayName("Test ArrayTaskList.getAll method")
    @Test
    public void testArrayTaskListGetAll() {
        Integer expectedSize = 0;
        assertEquals(expectedSize, arrayTaskList.size());

        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        expectedSize = 3;
        assertEquals(expectedSize, arrayTaskList.size());

        arrayTaskList.remove(task3);
        expectedSize = 2;
        assertEquals(expectedSize, arrayTaskList.size());

    }

    @AfterEach
    public void tearDown(){
        arrayTaskList.remove(task1);
        arrayTaskList.remove(task2);
        arrayTaskList.remove(task3);
    }

}