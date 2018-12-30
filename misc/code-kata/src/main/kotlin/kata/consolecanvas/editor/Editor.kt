package kata.consolecanvas.editor

import kata.consolecanvas.common.ignore
import kata.consolecanvas.editor.commands.*

/**
 * Handles user's input and mediates between all remaining components.
 */
class Editor(val editorContext: EditorContext, val commandParser: CommandParser) {

    fun run() {
        while (true) {
            displayMessage("enter command: ")
            executeCommand(readLine()!!)
            displayCanvas()
        }
    }

    private fun displayMessage(message: String) = print(message)

    private fun executeCommand(commandString: String) {
        val command = commandParser.parse(commandString)
        command.execute()

        with(editorContext.commandHistory) {
            when (command) {
                is UndoableCommand -> push(command)
                !is UndoCommand -> clear()
                else -> ignore
            }
        }
    }

    private fun displayCanvas() = println(editorContext.activeCanvas)
}
