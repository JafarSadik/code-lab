package kata.consolecanvas.editor.commands

import kata.consolecanvas.editor.EditorContext
import kata.consolecanvas.editor.commands.builders.*

/**
 * Encapsulates logic for building commands from their text representation
 */
class CommandParser(private val editorContext: EditorContext) {

    private val commandBuilders = listOf(QuitCommandBuilder, NewCommandBuilder, ClearCommandBuilder,
            FillCommandBuilder, DrawLineCommandBuilder, DrawRectCommandBuilder, UndoCommandBuilder)

    fun parse(commandString: String): Command = parseTrimmed(commandString.trim())

    private fun parseTrimmed(commandString: String): Command {
        val commandBuilder = commandBuilders.find { it.canHandle(commandString) }

        return commandBuilder?.build(editorContext, commandString) ?: UnknownCommand(commandString)
    }
}








