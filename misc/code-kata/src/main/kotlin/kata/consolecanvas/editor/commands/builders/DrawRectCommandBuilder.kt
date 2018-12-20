package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.canvas.Point
import kata.consolecanvas.editor.EditorContext
import kata.consolecanvas.editor.commands.Command
import kata.consolecanvas.editor.commands.DrawRectangleCommand

/**
 * Builds commands of type DrawRectangleCommand
 * syntax: R x1 y1 x2 y2
 */
class DrawRectCommandBuilder : CommandBuilder(regex = """R\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)""".toRegex()) {

    override fun build(editorContext: EditorContext, regexGroups: List<String>): Command {
        val (_, x1, y1, x2, y2) = regexGroups
        val upperLeftCorner = Point(x1.toInt(), y1.toInt())
        val lowerRightCorner = Point(x2.toInt(), y2.toInt())
        return DrawRectangleCommand(editorContext.activeCanvas, upperLeftCorner, lowerRightCorner)
    }
}