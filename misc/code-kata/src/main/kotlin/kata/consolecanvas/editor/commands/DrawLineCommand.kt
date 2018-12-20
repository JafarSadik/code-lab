package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.Canvas2D
import kata.consolecanvas.canvas.Point

/**
 * The command draws a line between two points. The task is delegated to Canvas2D
 */
data class DrawLineCommand(val canvas: Canvas2D, val from: Point, val to: Point) : Command {

    override fun execute() {
        canvas.drawLine(from, to)
    }
}