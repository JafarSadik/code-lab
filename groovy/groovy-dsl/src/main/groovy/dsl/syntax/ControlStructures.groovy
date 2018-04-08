package dsl.syntax

import dsl.HasRobot

trait ControlStructures implements HasRobot {
    def times(int n, Closure closure) {
        n.times {
            closure()
        }
    }
}