package dsl

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows

class RobotDSLTest {
    RobotDSL robotDsl = new RobotDSL()

    @Test
    void "Run robot closure DSL"() {
        robotDsl.execute {
            moveDown 10
            moveUp 5
            moveRight 10
            moveLeft 5
        }
        assertEquals(new Robot(x: 5, y: 5), robotDsl.robot)
    }

    @Test
    void "Run robot text DSL"() {
        robotDsl.execute """
            moveTo 0, 0
            moveDown 10
            moveRight 25
        """
        assertEquals(new Robot(x: 25, y: 10), robotDsl.robot)
    }

    @Test
    void "Move right command"() {
        assertEquals(new Robot(20, 0), robotDsl.execute { moveRight 20 }.robot)
        assertEquals(new Robot(-80, 0), robotDsl.execute { moveRight(-100) }.robot)
    }

    @Test
    void "Move left command"() {
        assertEquals(new Robot(-20, 0), robotDsl.execute { moveLeft 20 }.robot)
        assertEquals(new Robot(80, 0), robotDsl.execute { moveLeft(-100) }.robot)
    }

    @Test
    void "Move up command"() {
        assertEquals(new Robot(0, -20), robotDsl.execute { moveUp 20 }.robot)
        assertEquals(new Robot(0, 80), robotDsl.execute { moveUp(-100) }.robot)
    }

    @Test
    void "Move down command"() {
        assertEquals(new Robot(0, 20), robotDsl.execute { moveDown 20 }.robot)
        assertEquals(new Robot(0, -80), robotDsl.execute { moveDown(-100) }.robot)
    }

    @Test
    void "Control structure 'times' expected to execute the provided closure N-times"() {
        robotDsl.execute """
            moveTo 0, 0
            times(10) {
                moveRight 1
                moveDown 1
            }
        """
        assertEquals(new Robot(10, 10), robotDsl.robot)
    }

    @Test
    void "Location assertion should throw AssertionError when condition not met"() {
        // First check a positive case that should pass without errors
        robotDsl.execute {
            moveTo 0, 0
            moveRight 10
            assertLocation 10, 0
        }

        // Now make sure that a proper error is thrown when assertion fails
        assertThrows(AssertionError.class, {
            robotDsl.execute {
                moveDown 10
                assertLocation 25, 25 // <---- Wrong!
            }
        })
    }
}
