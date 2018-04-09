package dsl

import domain.Robot
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class RobotDSLTest {
    final def robot = new Robot()
    final def robotDSL = new RobotDSL(robot)

    @Test
    void "should execute an empty closure DSL"() {
        robotDSL.execute {}
    }

    @Test
    void "should execute any valid closure DSL"() {
        robotDSL.execute {
            moveDown 10
            moveUp 5
            moveRight 10
            moveLeft 5
        }
        assertEquals(new Robot(x: 5, y: 5), robot)
    }

    @Test
    void "should execute an empty text DSL"() {
        robotDSL.execute ""
    }

    @Test
    void "should execute any valid text DSL"() {
        robotDSL.execute """
            moveTo 0, 0
            moveDown 10
            moveRight 25
        """
        assertEquals(new Robot(x: 25, y: 10), robot)
    }

    @Test
    void "it's possible to access robot context from closure DSL"() {
        robot.setLocation(10, 100)
        robotDSL.execute {
            assert x == 10 && y == 100
        }
    }

    @Test
    void "it's possible to access robot context from text DSL"() {
        robot.setLocation(10, 100)
        robotDSL.execute """
            assert x == 10 && y == 100
        """
    }
}
