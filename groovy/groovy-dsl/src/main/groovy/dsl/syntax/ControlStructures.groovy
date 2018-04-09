package dsl.syntax

trait ControlStructures implements Syntax {
    Syntax times(int n, Closure<Syntax> closure) {
        n.times {
            closure()
        }
        this
    }
}