package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DrawRectangleCommandTest {

    @Mock
    lateinit var canvasMock: Canvas2D

    val savedCanvas = CanvasData(100)
    val upperLeftCorner = Point(5, 5)
    val lowerRightCorner = Point(10, 10)

    @Test
    fun `DrawRectangle command should draw a rectangle given by two corners`() {
        DrawRectangleCommand(canvasMock, upperLeftCorner, lowerRightCorner).execute()
        verify(canvasMock).drawRect(upperLeftCorner, lowerRightCorner)
    }

    @Test
    fun `DrawRectangle command should support undo operation`() {
        // The command should save canvas state upon execution
        val drawRectangleCommand = DrawRectangleCommand(canvasMock, upperLeftCorner, lowerRightCorner)
        `when`(canvasMock.getData()).thenReturn(savedCanvas)
        drawRectangleCommand.execute()

        // ... and be able to restore it later
        drawRectangleCommand.undo()
        verify(canvasMock).setData(savedCanvas)
    }
}