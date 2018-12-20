package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.EditorContext
import kata.consolecanvas.editor.commands.QuitCommand

/**
 * Builds commands of type QuitCommand
 * syntax: Q
 */
class QuitCommandBuilder : CommandBuilder("""Q""".toRegex()) {

    override fun build(editorContext: EditorContext, regexGroups: List<String>) = QuitCommand()
}
