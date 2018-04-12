package dsl.syntax.sugar

import dsl.syntax.Context

trait Then {
    def then = this

    def then() {
        this
    }

    Context then(Context expr) {
        expr
    }

    Context then(Closure<Context> closure) {
        closure()
    }
}