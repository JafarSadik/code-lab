package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.Canvas2D
import kata.consolecanvas.editor.EditorContext
import org.junit.Assert.assertEquals
import org.junit.Test

class NewCommandTest {

    @Test
    fun `New command should create a new canvas and save it in editor's context`() {
        // Given the editor context with an initial canvas 100x100
        val editorContext = EditorContext(Canvas2D(100, 100))

        // When executing the 'New' command we expect to create a new canvas
        NewCommand(editorContext, 15, 10).execute()
        assertEquals(15, editorContext.activeCanvas.width)
        assertEquals(10, editorContext.activeCanvas.height)
    }
}