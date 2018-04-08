package dsl

import dsl.executors.DSLExecutors
import dsl.syntax.Assertions
import dsl.syntax.BasicCommands
import dsl.syntax.ControlStructures
import shared.Robot

class RobotDSL implements DSLExecutors, BasicCommands, ControlStructures, Assertions {
    @Delegate
    final Robot robot;

    RobotDSL(Robot robot) {
        this.robot = robot
    }
}
