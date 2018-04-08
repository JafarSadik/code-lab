package dsl.syntax

import dsl.HasRobot

trait BasicCommands implements HasRobot {
    def moveTo(int x, int y) {
        robot.x = x
        robot.y = y
    }

    def moveUp(int distance) {
        robot.y -= distance
    }

    def moveDown(int distance) {
        robot.y += distance
    }

    def moveLeft(int distance) {
        robot.x -= distance
    }

    def moveRight(int distance) {
        robot.x += distance
    }
}
