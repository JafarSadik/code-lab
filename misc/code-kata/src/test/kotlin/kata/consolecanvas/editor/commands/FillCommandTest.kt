package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.Canvas2D
import kata.consolecanvas.canvas.Point
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FillCommandTest {

    @Mock
    lateinit var canvasMock: Canvas2D

    @Test
    fun `Fill command should fill an area with a uniform colour starting from a given point`() {
        val startPoint = Point(5, 5)
        val fillColour = 'o'

        FillCommand(canvasMock, startPoint, fillColour).execute()
        verify(canvasMock).fill(startPoint, fillColour)
        verifyNoMoreInteractions(canvasMock)
    }
}