package dsl

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class AssertionsTest extends RobotDSLTest {
    @Test
    void "Location assertion should throw AssertionError when condition not met"() {
        // First check a positive case that should pass without errors
        robotDSL.execute {
            moveTo 0, 0
            moveRight 10
            assertLocation 10, 0
        }

        // Now make sure that a proper error is thrown when assertion fails
        assertThrows(AssertionError.class, {
            robotDSL.execute {
                moveDown 10
                assertLocation 25, 25 // <---- Wrong!
            }
        })
    }
}
