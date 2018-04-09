package dsl

import domain.Robot
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class ControlStructuresTest {
    final def robot = new Robot()
    final def robotDSL = new RobotDSL(robot)

    @Test
    void "control structure 'times' expected to execute the provided closure N-times"() {
        robotDSL.execute """
            moveTo 0, 0
            times(10) {
                moveRight 1
                moveDown 1
            }
        """
        assertEquals(new Robot(10, 10), robot)
    }
}
