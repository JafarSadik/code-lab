package kata.consolecanvas.editor

import kata.consolecanvas.editor.commands.CommandParser

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

    private fun executeCommand(command: String) = commandParser.parse(command).execute()

    private fun displayCanvas() = println(editorContext.activeCanvas)
}
