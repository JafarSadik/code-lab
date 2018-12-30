package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.*

/**
 * The command fills an area with a uniform colour. The task is delegated to Canvas2D
 */
data class FillCommand(val canvas: Canvas2D, val startPoint: Point, val colour: Colour) : UndoableCommand {
    private lateinit var savedCanvas: CanvasData

    override fun execute() {
        savedCanvas = canvas.getData()
        canvas.fill(startPoint, colour)
    }

    override fun undo() {
        canvas.setData(savedCanvas)
    }
}