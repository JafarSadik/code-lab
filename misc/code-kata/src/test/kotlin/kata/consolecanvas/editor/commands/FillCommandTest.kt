package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FillCommandTest {
    @Mock
    lateinit var canvasMock: Canvas2D

    val savedCanvas = CanvasData(100)
    val startPoint = Point(5, 5)
    val fillColour = 'o'

    @Test
    fun `Fill command should fill an area with a uniform colour starting from a given point`() {
        FillCommand(canvasMock, startPoint, fillColour).execute()
        verify(canvasMock).fill(startPoint, fillColour)
    }

    @Test
    fun `Fill command should support undo operation`() {
        // The command should save canvas state upon execution
        val fillCommand = FillCommand(canvasMock, startPoint, fillColour)
        `when`(canvasMock.getData()).thenReturn(savedCanvas)
        fillCommand.execute()

        // ... and be able to restore it later
        fillCommand.undo()
        verify(canvasMock).setData(savedCanvas)
    }
}