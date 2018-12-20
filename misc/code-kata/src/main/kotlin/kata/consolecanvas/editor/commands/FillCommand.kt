package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.*

/**
 * The command fills an area with a uniform colour. The task is delegated to Canvas2D
 */
data class FillCommand(val canvas: Canvas2D, val startPoint: Point, val colour: Colour) : Command {

    override fun execute() {
        canvas.fill(startPoint, colour)
    }
}