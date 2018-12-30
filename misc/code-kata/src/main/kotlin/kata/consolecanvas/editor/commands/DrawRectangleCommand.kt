package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.*

/**
 * The command draws a rectangle given two points. The task is delegated to Canvas2D
 */
data class DrawRectangleCommand(val canvas: Canvas2D, val upperLeftCorner: Point, val lowerRightCorner: Point) : UndoableCommand {
    private lateinit var savedCanvas: CanvasData

    override fun execute() {
        savedCanvas = canvas.getData()
        canvas.drawRect(upperLeftCorner, lowerRightCorner)
    }

    override fun undo() {
        canvas.setData(savedCanvas)
    }
}