package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.canvas.Point
import kata.consolecanvas.editor.EditorContext
import kata.consolecanvas.editor.commands.Command
import kata.consolecanvas.editor.commands.FillCommand


/**
 * Builds commands of type FillCommand
 * syntax: B x y colour
 */
class FillCommandBuilder : CommandBuilder(regex = """B\s+(\d+)\s+(\d+)\s+(\w)""".toRegex()) {

    override fun build(editorContext: EditorContext, regexGroups: List<String>): Command {
        val (_, x, y, colour) = regexGroups
        val startPoint = Point(x.toInt(), y.toInt())
        return FillCommand(editorContext.activeCanvas, startPoint, colour[0])
    }
}
