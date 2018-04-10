package dsl.syntax.sugar

import dsl.syntax.shapes.Circle

trait Builders {
    Circle circle(int x, int y, int r) {
        new Circle(x, y, r)
    }
}