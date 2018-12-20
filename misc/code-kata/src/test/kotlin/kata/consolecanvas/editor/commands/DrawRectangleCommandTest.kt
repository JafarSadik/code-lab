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
class DrawRectangleCommandTest {

    @Mock
    lateinit var canvasMock: Canvas2D

    @Test
    fun `DrawRectangle command should draw a rectangle given by two corners`() {
        val (upperLeftCorner, lowerRightCorner) = Pair(Point(5, 5), Point(10, 10))

        DrawRectangleCommand(canvasMock, upperLeftCorner, lowerRightCorner).execute()
        verify(canvasMock).drawRect(upperLeftCorner, lowerRightCorner)
        verifyNoMoreInteractions(canvasMock)
    }
}