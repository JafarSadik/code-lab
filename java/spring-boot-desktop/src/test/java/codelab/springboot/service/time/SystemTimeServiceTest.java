package codelab.springboot.service.time;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SystemTimeServiceTest {
    final TimeService timeService = new SystemTimeService();

    @Test
    public void getTimeTest() {
        assertNotNull(timeService.getTime());
    }
}