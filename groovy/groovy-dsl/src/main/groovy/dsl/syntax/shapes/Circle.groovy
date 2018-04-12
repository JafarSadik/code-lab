package dsl.syntax.shapes

import domain.Robot

class Circle implements ShapePredicate {
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

    private double distance(int x1, int y1, int x2, int y2) {
        Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))
    }
}
