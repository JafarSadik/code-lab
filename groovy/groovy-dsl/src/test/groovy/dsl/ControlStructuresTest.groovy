package dsl

import org.junit.jupiter.api.Test
import shared.Robot

import static org.junit.jupiter.api.Assertions.assertEquals

class ControlStructuresTest extends RobotDSLTest {
    @Test
    void "Control structure 'times' expected to execute the provided closure N-times"() {
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
