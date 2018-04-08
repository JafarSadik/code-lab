package dsl

import org.junit.jupiter.api.Test
import shared.Robot

import static org.junit.jupiter.api.Assertions.assertEquals

class BasicCommandsTest extends RobotDSLTest {
    @Test
    void "Move right command"() {
        assertEquals(new Robot(20, 0), robotDSL.execute { moveRight 20 }.robot)
        assertEquals(new Robot(-80, 0), robotDSL.execute { moveRight(-100) }.robot)
    }

    @Test
    void "Move left command"() {
        assertEquals(new Robot(-20, 0), robotDSL.execute { moveLeft 20 }.robot)
        assertEquals(new Robot(80, 0), robotDSL.execute { moveLeft(-100) }.robot)
    }

    @Test
    void "Move up command"() {
        assertEquals(new Robot(0, -20), robotDSL.execute { moveUp 20 }.robot)
        assertEquals(new Robot(0, 80), robotDSL.execute { moveUp(-100) }.robot)
    }

    @Test
    void "Move down command"() {
        assertEquals(new Robot(0, 20), robotDSL.execute { moveDown 20 }.robot)
        assertEquals(new Robot(0, -80), robotDSL.execute { moveDown(-100) }.robot)
    }
}
