package testLab3;

import org.junit.jupiter.api.Test;
import tasks.services.TaskIO;

import static org.junit.jupiter.api.Assertions.*;

public class TestWBTGetInterval {

    @Test
    public void TestWithValidDays() {
        int interval = TaskIO.getIntervalFromText("[1 day]");
        assertEquals(interval, 86400);
    }

    @Test
    public void TestWithValidHours() {
        int interval = TaskIO.getIntervalFromText("[10 hours]");
        assertEquals(interval, 36000);
    }

    @Test
    public void TestWithValidHoursAndMinutes() {
        int interval = TaskIO.getIntervalFromText("[1 hour 10 minutes]");
        assertEquals(interval, 4200);
    }

    @Test
    public void TestWithValidHoursAndSeconds() {
        int interval = TaskIO.getIntervalFromText("[2 hours 10 seconds]");
        assertEquals(interval, 7801);
    }

    @Test
    public void TestWithNoText() {
        assertThrows(NumberFormatException.class, () -> TaskIO.getIntervalFromText("[]"));
    }

}
