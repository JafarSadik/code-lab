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
class DrawLineCommandTest {

    @Mock
    lateinit var canvasMock: Canvas2D

    @Test
    fun `DrawLine command should draw a line between two points`() {
        val (from, to) = Pair(Point(5, 5), Point(10, 5))

        DrawLineCommand(canvasMock, from, to).execute()
        verify(canvasMock).drawLine(from, to)
        verifyNoMoreInteractions(canvasMock)
    }
}