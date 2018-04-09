package dsl

import domain.Robot
import org.junit.jupiter.api.Test

class SyntacticSugarTest {
    final def robot = new Robot()
    final def robotDSL = new RobotDSL(robot)

    @Test
    void "should be possible to chain multiple commands in a single line"() {
        robotDSL.execute { moveTo 0, 0 moveDown 10 moveRight 10 moveLeft 10 moveRight 10 assertLocation 10, 10 }
        assert robot == new Robot(10, 10)

        robotDSL.execute "moveTo 0, 0 moveDown 10 moveRight 10 moveLeft 10 moveRight 10 assertLocation 10, 10"
        assert robot == new Robot(10, 10)
    }

    @Test
    void "syntactic sugar 'and' requires parenthesis"() {
        robotDSL.execute { moveTo 0, 0 and() moveDown 10 and() moveRight 10 and() assertLocation 10, 10 }
        assert robot == new Robot(10, 10)

        robotDSL.execute "moveTo 0, 0 and() moveDown 10 and() moveRight 10 and() assertLocation 10, 10"
        assert robot == new Robot(10, 10)
    }

    @Test
    void "syntactic sugar 'then' requires parenthesis"() {
        robotDSL.execute { moveTo 0, 0 then() moveDown 10 then() moveRight 10 then() assertLocation 10, 10 }
        assert robot == new Robot(10, 10)

        robotDSL.execute "moveTo 0, 0 then() moveDown 10 then() moveRight 10 then() assertLocation 10, 10"
        assert robot == new Robot(10, 10)
    }

    @Test
    void "should be possible to chain expressions 'and then' without parenthesis"() {
        robotDSL.execute {
            moveTo 0, 0 and then moveDown 10 and then moveRight 10
            moveUp 0 and then moveLeft 0
            assertLocation 10, 10
        }
        assert robot == new Robot(10, 10)

        robotDSL.execute """
            moveTo 0, 0 and then moveDown 10 and then moveRight 10
            moveUp 0 and then moveLeft 0
            assertLocation 10, 10
         """
        assert robot == new Robot(10, 10)
    }

    @Test
    void "test 'and' with closure"() {
        robotDSL.execute {
            moveTo 0, 0 and { moveDown 10 moveRight 10 moveLeft 10 moveRight 10 }
            assertLocation 10, 10
        }
        assert robot == new Robot(10, 10)

        robotDSL.execute """
            moveTo 0, 0 and { moveDown 10 moveRight 10 moveLeft 10 moveRight 10 }
            assertLocation 10, 10
         """
        assert robot == new Robot(10, 10)
    }

    @Test
    void "test 'then' with closure"() {
        robotDSL.execute {
            moveTo 0, 0 and then moveDown 10 and {
                moveRight 10
                moveLeft 10
                moveRight 10
            }
            and then {
                println "robot at ($x, $y)"
                assertLocation 10, 10
            }
        }
        assert robot == new Robot(10, 10)

        robotDSL.execute """
           moveTo 0, 0 and then moveDown 10 and {
                moveRight 10
                moveLeft 10
                moveRight 10
            }
            and then {
                println "robot at (" + x + ", " + y + ")" 
                assertLocation 10, 10
            }
        """
        assert robot == new Robot(10, 10)
    }
}
