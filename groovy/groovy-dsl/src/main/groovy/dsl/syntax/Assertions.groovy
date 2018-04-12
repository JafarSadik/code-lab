package dsl.syntax

trait Assertions implements Context {
    Context assertLocation(int x, int y) throws AssertionError {
        assert x == robot.x && y == robot.y
        this
    }
}