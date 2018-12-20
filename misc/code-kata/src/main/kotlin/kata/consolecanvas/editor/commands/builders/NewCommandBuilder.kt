package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.EditorContext
import kata.consolecanvas.editor.commands.Command
import kata.consolecanvas.editor.commands.NewCommand

/**
 * Builds commands of type NewCommand
 * syntax: C
 */
class NewCommandBuilder : CommandBuilder(regex = """C\s+(\d+)\s+(\d+)""".toRegex()) {

    override fun build(editorContext: EditorContext, regexGroups: List<String>): Command {
        val (_, width, height) = regexGroups
        return NewCommand(editorContext, width.toInt(), height.toInt())
    }
}
