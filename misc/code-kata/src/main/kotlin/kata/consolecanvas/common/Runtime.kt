package kata.consolecanvas.common


interface Runtime {

    fun exit(exitCode: Int)
}

object DefaultRuntime : Runtime {

    override fun exit(exitCode: Int) {
        System.exit(exitCode)
    }
}