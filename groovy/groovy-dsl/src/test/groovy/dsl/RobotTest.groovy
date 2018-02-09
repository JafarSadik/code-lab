package dsl

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.*

class RobotTest {
    @Test
    void "Generated hashCode method test"() {
        // Expect the same hash code for two different instances sharing instance variables
        assertEquals(new Robot(x: 100, y: 115).hashCode(), new Robot(x: 100, y: 115).hashCode())
        assertEquals(new Robot(x: 0, y: 0).hashCode(), new Robot(x: 0, y: 0).hashCode())

        // Otherwise, most likely, they are different
        assertNotEquals(new Robot(x: 0, y: 0).hashCode(), new Robot(x: 20, y: 20).hashCode())
    }

    @Test
    void "Generated equals method test"() {
        // Expect two different instances to be equal if they share the same instance variables
        assertEquals(new Robot(x: 100, y: 115), new Robot(x: 100, y: 115))
        assertEquals(new Robot(x: 0, y: 0), new Robot(x: 0, y: 0))

        // Otherwise they cannot be equal
        assertNotEquals(new Robot(x: 0, y: 0), new Robot(x: 20, y: 20))
    }

    @Test
    void "Generated clone method test"() {
        def robot = new Robot(x: 100, y: 100)
        def robotClone = robot.clone()

        // A clone should be equal to the original instance
        assertEquals(robot, robotClone)

        // But those are two different instances
        assertNotSame(robot, robotClone)

        // And therefore changing the clone shouldn't affect the original instance
        robotClone.moveTo 0, 0
        assertTrue(robotClone.x == 0 && robotClone.y == 0)
        assertTrue(robot.x == 100 && robot.y == 100)
    }
}
