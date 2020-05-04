package testLab3;

import org.junit.jupiter.api.Test;
import tasks.services.TaskIO;

import static org.junit.jupiter.api.Assertions.*;

public class TestWBTGetInterval {

    @Test
    public void TestWithValidDays() {
        int interval = TaskIO.getIntervalFromText("[1 day]");
        assertEquals(86400, interval);
    }

    @Test
    public void TestWithValidHours() {
        int interval = TaskIO.getIntervalFromText("[10 hours]");
        assertEquals(36000, interval);
    }

    @Test
    public void TestWithValidHoursAndMinutes() {
        int interval = TaskIO.getIntervalFromText("[1 hour 10 minutes]");
        assertEquals(4200, interval);
    }

    @Test
    public void TestWithValidHoursAndSeconds() {
        int interval = TaskIO.getIntervalFromText("[2 hours 10 seconds]");
        assertEquals(7201, interval);
    }

    @Test
    public void TestWithNoText() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> TaskIO.getIntervalFromText(""));
    }

    @Test
    public void TestWithNegativeValues() {
        int interval = TaskIO.getIntervalFromText("[-10 hours 10 minutes]");
        assertEquals(-35400, interval);
    }

}
