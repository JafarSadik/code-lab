package dsl.syntax.shapes

import domain.Robot

class Circle implements Shape {
    private final int x, y, r

    Circle(int x, int y, int r) {
        this.x = x
        this.y = y
        this.r = r
    }

    @Override
    boolean test(Robot robot) {
        distance(x, y, robot.x, robot.y) < r
    }
}
