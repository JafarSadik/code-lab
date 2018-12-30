package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DrawLineCommandTest {

    @Mock
    lateinit var canvasMock: Canvas2D

    val savedCanvas = CanvasData(100)
    val lineFrom = Point(5, 5)
    val lineTo = Point(10, 5)

    @Test
    fun `DrawLine command should draw a line between two points`() {
        DrawLineCommand(canvasMock, lineFrom, lineTo).execute()
        verify(canvasMock).drawLine(lineFrom, lineTo)
    }

    @Test
    fun `DrawLine command should support undo operation`() {
        // The command should save canvas state upon execution
        val drawLineCommand = DrawLineCommand(canvasMock, lineFrom, lineTo)
        `when`(canvasMock.getData()).thenReturn(savedCanvas)
        drawLineCommand.execute()

        // ... and be able to restore it later
        drawLineCommand.undo()
        verify(canvasMock).setData(savedCanvas)
    }
}