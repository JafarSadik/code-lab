package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.canvas.Point
import kata.consolecanvas.editor.EditorContext
import kata.consolecanvas.editor.commands.Command
import kata.consolecanvas.editor.commands.DrawLineCommand

/**
 * Builds commands of type DrawLineCommand
 * syntax: L x1 y1 x2 y2
 */
class DrawLineCommandBuilder : CommandBuilder(regex = """L\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)""".toRegex()) {

    override fun build(editorContext: EditorContext, regexGroups: List<String>): Command {
        val (_, x1, y1, x2, y2) = regexGroups
        val lineFrom = Point(x1.toInt(), y1.toInt())
        val lineTo = Point(x2.toInt(), y2.toInt())
        return DrawLineCommand(editorContext.activeCanvas, lineFrom, lineTo)
    }
}
