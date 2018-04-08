package dsl.syntax

import dsl.HasRobot

trait Assertions implements HasRobot {
    def assertLocation(int x, int y) {
        assert x == robot.x && y == robot.y
    }
}