package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.EditorContext
import kata.consolecanvas.editor.commands.ClearCommand

/**
 * Builds commands of type ClearCommand
 * syntax: C
 */
object ClearCommandBuilder : CommandBuilder("""C""") {

    override fun build(editorContext: EditorContext, regexGroups: List<String>) = ClearCommand(editorContext.activeCanvas)
}
