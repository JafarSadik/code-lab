package dsl

import org.junit.jupiter.api.Test
import shared.Robot

import static org.junit.jupiter.api.Assertions.assertEquals

class DSLExecutorsTest extends RobotDSLTest {
    @Test
    void "Executor can run closure DSL"() {
        robotDSL.execute {
            moveDown 10
            moveUp 5
            moveRight 10
            moveLeft 5
        }
        assertEquals(new Robot(x: 5, y: 5), robot)
    }

    @Test
    void "It's possible to access robot context from closure DSL"() {
        robot.setLocation(10, 100)
        robotDSL.execute {
            assert x == 10 && y == 100
        }
    }

    @Test
    void "Executor can run text DSL"() {
        robotDSL.execute """
            moveTo 0, 0
            moveDown 10
            moveRight 25
        """
        assertEquals(new Robot(x: 25, y: 10), robot)
    }

    @Test
    void "It's possible to access robot context from text DSL"() {
        robot.setLocation(10, 100)
        robotDSL.execute """
            assert x == 10 && y == 100
        """
    }
}
