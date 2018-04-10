package dsl.syntax

import dsl.syntax.shapes.Shape

trait ControlStructures implements Syntax {
    Syntax times(int n, Closure<Syntax> closure) {
        n.times {
            closure()
        }
        this
    }

    Syntax whileIn(Shape shape, Closure<Syntax> closure) {
        while (shape.test(robot)) {
            closure()
        }
        this
    }
}