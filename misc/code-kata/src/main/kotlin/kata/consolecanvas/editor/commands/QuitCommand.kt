package kata.consolecanvas.editor.commands

import kata.consolecanvas.common.DefaultRuntime
import kata.consolecanvas.common.Runtime

/**
 * The command terminates application
 */
class QuitCommand(val runtime: Runtime = DefaultRuntime) : Command {

    override fun execute() {
        runtime.exit(0)
    }
}