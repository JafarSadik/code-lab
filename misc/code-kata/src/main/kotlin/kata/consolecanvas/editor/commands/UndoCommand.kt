package kata.consolecanvas.editor.commands

import kata.consolecanvas.editor.EditorContext

/**
 * The command undoes the previously executed command
 */
data class UndoCommand(val editorContext: EditorContext) : Command {

    override fun execute() = with(editorContext.commandHistory) {
        if (isNotEmpty()) {
            val previousCommand = pop()
            previousCommand.undo()
        }
    }
}