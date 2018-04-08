package dsl

import shared.Robot

abstract class RobotDSLTest {
    final def robot = new Robot()
    final def robotDSL = new RobotDSL(robot)
}
