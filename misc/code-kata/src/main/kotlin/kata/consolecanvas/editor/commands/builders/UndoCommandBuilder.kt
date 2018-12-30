package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.EditorContext
import kata.consolecanvas.editor.commands.UndoCommand

/**
 * Builds commands of type UndoCommand
 * syntax: U
 */
object UndoCommandBuilder : CommandBuilder("""U""") {

    override fun build(editorContext: EditorContext, regexGroups: List<String>) = UndoCommand(editorContext)
}
