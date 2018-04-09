package dsl.syntax

trait Commands implements Syntax {
    Syntax moveTo(int x, int y) {
        robot.x = x
        robot.y = y
        this
    }

    Syntax moveUp(int distance) {
        robot.y -= distance
        this
    }

    Syntax moveDown(int distance) {
        robot.y += distance
        this
    }

    Syntax moveLeft(int distance) {
        robot.x -= distance
        this
    }

    Syntax moveRight(int distance) {
        robot.x += distance
        this
    }
}
