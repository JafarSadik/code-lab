package dsl.executors

trait ClosureDSLExecutor {
    def execute(Closure dslClosure) {
        dslClosure.delegate = this
        dslClosure()
        this
    }
}
