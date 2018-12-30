package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.*

/**
 * The command draws a line between two points. The task is delegated to Canvas2D
 */
data class DrawLineCommand(val canvas: Canvas2D, val from: Point, val to: Point) : UndoableCommand {
    private lateinit var savedCanvas: CanvasData

    override fun execute() {
        savedCanvas = canvas.getData()
        canvas.drawLine(from, to)
    }

    override fun undo() {
        canvas.setData(savedCanvas)
    }
}