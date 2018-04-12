package dsl.syntax

trait Commands implements Context {
    Context moveTo(int x, int y) {
        robot.x = x
        robot.y = y
        this
    }

    Context moveUp(int distance) {
        robot.y -= distance
        this
    }

    Context moveDown(int distance) {
        robot.y += distance
        this
    }

    Context moveLeft(int distance) {
        robot.x -= distance
        this
    }

    Context moveRight(int distance) {
        robot.x += distance
        this
    }
}
