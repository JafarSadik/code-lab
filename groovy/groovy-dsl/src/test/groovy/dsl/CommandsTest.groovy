package dsl

import domain.Robot
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class CommandsTest {
    final def robotDSL = new RobotDSL(new Robot())

    @Test
    void "move right command"() {
        assertEquals(new Robot(20, 0), robotDSL.execute { moveRight 20 }.robot)
        assertEquals(new Robot(-80, 0), robotDSL.execute { moveRight(-100) }.robot)
    }

    @Test
    void "move left command"() {
        assertEquals(new Robot(-20, 0), robotDSL.execute { moveLeft 20 }.robot)
        assertEquals(new Robot(80, 0), robotDSL.execute { moveLeft(-100) }.robot)
    }

    @Test
    void "move up command"() {
        assertEquals(new Robot(0, -20), robotDSL.execute { moveUp 20 }.robot)
        assertEquals(new Robot(0, 80), robotDSL.execute { moveUp(-100) }.robot)
    }

    @Test
    void "move down command"() {
        assertEquals(new Robot(0, 20), robotDSL.execute { moveDown 20 }.robot)
        assertEquals(new Robot(0, -80), robotDSL.execute { moveDown(-100) }.robot)
    }
}
