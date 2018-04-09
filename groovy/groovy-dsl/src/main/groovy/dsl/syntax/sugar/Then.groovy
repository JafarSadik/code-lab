package dsl.syntax.sugar

import dsl.syntax.Syntax

trait Then {
    def then = this

    def then() {
        this
    }

    Syntax then(Syntax expr) {
        expr
    }

    Syntax then(Closure<Syntax> closure) {
        closure()
    }
}