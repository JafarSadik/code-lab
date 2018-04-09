package dsl

import domain.Robot
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class AssertionsTest {
    final def robotDSL = new RobotDSL(new Robot())

    @Test
    void "assertLocation should fail only when condition not met"() {
        robotDSL.execute {
            moveTo 0, 0
            moveRight 10
            assertLocation 10, 0
        }

        assertThrows(AssertionError.class, {
            robotDSL.execute {
                moveDown 10
                assertLocation 25, 25 // <---- Wrong!
            }
        })
    }
}
