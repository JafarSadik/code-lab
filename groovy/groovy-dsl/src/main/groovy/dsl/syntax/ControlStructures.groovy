package dsl.syntax

import dsl.syntax.shapes.ShapePredicate

trait ControlStructures implements Context {
    Context times(int n, Closure<Context> closure) {
        n.times {
            closure()
        }
        this
    }

    Context whileIn(ShapePredicate shape, Closure<Context> closure) {
        while (shape.test(robot)) {
            closure()
        }
        this
    }
}