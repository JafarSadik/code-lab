package dsl.syntax.sugar

import dsl.syntax.Context

trait And {
    def and = this

    def and() {
        this
    }

    Context and(Context expr) {
        expr
    }

    Context and(Closure<Context> closure) {
        closure()
    }
}