package dsl.syntax.shapes

import domain.Robot

import java.util.function.Predicate

trait Shape implements Predicate<Robot> {
    double distance(int x1, int y1, int x2, int y2) {
        Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))
    }
}
