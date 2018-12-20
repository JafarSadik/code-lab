package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.Canvas2D
import kata.consolecanvas.canvas.Point

/**
 * The command draws a rectangle given two points. The task is delegated to Canvas2D
 */
data class DrawRectangleCommand(val canvas: Canvas2D, val upperLeftCorner: Point, val lowerRightCorner: Point) : Command {

    override fun execute() {
        canvas.drawRect(upperLeftCorner, lowerRightCorner)
    }
}