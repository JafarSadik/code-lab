package dsl.syntax.sugar

import dsl.syntax.Syntax

trait And {
    def and = this

    def and() {
        this
    }

    Syntax and(Syntax expr) {
        expr
    }

    Syntax and(Closure<Syntax> closure) {
        closure()
    }
}