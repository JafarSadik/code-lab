package kata.consolecanvas.editor.commands

import java.io.PrintStream

/**
 * The command used for unknown commands or invalid syntax.
 */
class UnknownCommand(val command: String, val output: PrintStream = System.out) : Command {

    override fun execute() {
        output.println("Unknown command or invalid syntax")
    }
}